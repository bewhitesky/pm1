package com.cszx.pm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cszx.common.excel.ExcelBean;
import com.cszx.common.excel.ExcelUtil;
import com.cszx.common.model.PageHelp;
import com.cszx.pm.dao.task.TaskDao;
import com.cszx.pm.dao.task.TaskTreeDao;
import com.cszx.pm.model.task.Task;
import com.cszx.pm.model.task.TaskDto;
import com.cszx.pm.model.task.TaskHistory;
import com.cszx.pm.model.task.TaskHistoryVo;
import com.cszx.pm.model.task.TaskTree;
import com.cszx.pm.model.task.TaskVo;
import com.cszx.pm.service.TaskService;
import com.cszx.util.StringUtil;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	@Resource
	private TaskDao taskDao;
	@Resource
	private TaskTreeDao taskTreeDao;

	@Override
	public List<Task> selectTask(Map<String, Object> taskMap) {
		return taskDao.selectTask(taskMap);
	}

	@Override
	public void addTask(TaskVo taskVo) {
		taskDao.addTask(taskVo);
	}

	@Override
	public void updateTask(TaskVo taskVo) {
		taskDao.updateTask(taskVo);
	}

	@Override
	public void updateTaskActualDay(String id) {
		taskDao.updateTaskActualDay(id);
	}

	@Override
	public void updateTaskActualDay2(String id) {
		taskDao.updateTaskActualDay2(id);
	}

	@Override
	public void deleteTask(String id) {
		taskDao.deleteTask(id);
		taskDao.deleteTaskByPId(id);
	}

	@Override
	public List<Task> selectTaskByUser(Map<String, Object> taskMap) {
		return taskDao.selectTaskByUser(taskMap);
	}

	@Override
	public PageHelp<TaskTree> getTasks(Map<String, Object> map) {
		List<TaskTree> treeList = new ArrayList<TaskTree>();
		treeList = buildTaskTree(map);
		int rows = (int) map.get("rows");
		int page = (int) map.get("page");
		PageHelp<TaskTree> result = new PageHelp<TaskTree>(sortList(treeList), rows, page);
		return result;
	}

	public List<TaskTree> buildTaskTree(Map<String, Object> map) {
		// taskTreeDao.getSumActualDay();
		map.put("level", 1);
		List<TaskTree> treeList1 = taskTreeDao.getTasks(map);// 查询所有符合条件的一层节点
		String pids = "";
		for (TaskTree node : treeList1) {
			pids = pids + node.getId() + ",";
		}
		map.put("level", 2);
		map.put("pids", pids);
		List<TaskTree> treeList2 = taskTreeDao.getTasks(map);// 查询所有符合条件的二层节点
		pids = "";
		for (TaskTree node : treeList2) {
			pids = pids + node.getId() + ",";
		}
		map.put("level", 3);
		map.put("pids", pids);
		List<TaskTree> treeList3 = taskTreeDao.getTasks(map);// 查询所有符合条件的二层节点
		treeList2 = buildTreeNode(treeList2, treeList3);
		treeList1 = buildTreeNode(treeList1, treeList2);
		return treeList1;
	}

	public List<TaskTree> buildTreeNode(List<TaskTree> parents, List<TaskTree> children) {
		String oldPid = "";// 上一次循环的Pid，等于""时表示第一次循环
		List<TaskTree> child = new ArrayList<TaskTree>();
		TaskTree tree2 = new TaskTree();// 2层任务节点
		int count = 1;
		for (TaskTree node : children) {
			if (oldPid.equals(node.getPId())) {// 当oldPid等于本次循环pid时，更新oldPid，child
				oldPid = node.getPId();
				child.add(node);
			} else if ("".equals(oldPid)) {// 当oldPid等于""时，为第一次循环，初始化oldPid，child，tree2值
				oldPid = node.getPId();
				child.add(node);
				tree2 = this.findPreNode(node.getPId());
			} else {// 当oldPid不等于以上两种情况时，为tree2设置子节点child，同时存入treeList2，更新oldPid，child，tree2
				if (tree2 != null) {
					tree2.setChildren(child);
				}
				if (checkNodeExist(tree2, parents)) {
					parents.add(tree2);
				}
				tree2 = this.findPreNode(node.getPId());
				oldPid = node.getPId();
				child = new ArrayList<TaskTree>();
				child.add(node);
			}
			if (count == children.size()) {
				if (tree2 != null) {
					tree2.setChildren(child);
				}
				if (checkNodeExist(tree2, parents)) {
					parents.add(tree2);
				}
			} else {
				count++;
			}
		}
		return parents;
	}

	public List<TaskTree> createTaskTree(Map<String, Object> map) {
		List<TaskTree> treeList1 = new ArrayList<TaskTree>();
		List<TaskTree> treeList2 = new ArrayList<TaskTree>();
		List<TaskTree> treeList3 = new ArrayList<TaskTree>();
		List<TaskTree> list2 = null;
		List<TaskTree> list3 = null;
		map.put("level", 1);
		List<TaskTree> sList1 = taskTreeDao.getTasks(map);// 查询所有符合条件的一层节点
		for (TaskTree tree : sList1) {
			if (checkNodeExist(tree, treeList1)) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("level", 2);
				map1.put("pId", tree.getId());
				map1.put("taskName", "");
				list2 = taskTreeDao.getTasks(map1);
				if (list2.size() > 0) {
					for (TaskTree tree2 : list2) {
						map1.put("level", 3);
						map1.put("pId", tree2.getId());
						list3 = taskTreeDao.getTasks(map1);
						if (list3.size() > 0) {
							tree2.setChildren(list3);
							tree2.setState("closed");
							treeList3.addAll(list3);
						}
						tree2.setIconCls("icon-tree-level2");
						treeList2.add(tree2);
					}
				}
				tree.setChildren(list2);
				treeList1.add(tree);
			}
		}
		map.put("level", 2);
		List<TaskTree> sList2 = taskTreeDao.getTasks(map);// 查询所有符合条件的二层节点
		for (TaskTree tree : sList2) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("level", 3);
			map2.put("pId", tree.getId());
			map2.put("taskName", "");
			list3 = taskTreeDao.getTasks(map2);
			if (list3.size() > 0) {
				tree.setChildren(list3);
				tree.setState("closed");
			}
			tree.setIconCls("icon-tree-level2");

			if (checkNodeExist(tree, treeList2)) {
				treeList2.add(tree);// 把节点2添加到treeList2
			}
			if (checkNodeExist(findRootNode(tree.getPId()), treeList1)) {// 判断是否存在节点1
				treeList1.add(findRootNode(tree.getPId()));// 不存在则添加到treeList1
			}
		}
		map.put("level", 3);
		List<TaskTree> sList3 = taskTreeDao.getTasks(map);// 查询所有符合条件的三层节点
		for (TaskTree tree : sList3) {
			if (checkNodeExist(tree, treeList3)) {
				treeList3.add(tree);// 把节点3添加到treeList3
			}
			if (checkNodeExist(findPreNode(tree.getPId()), treeList2)) {// 判断是否存在节点2
				treeList2.add(findPreNode(tree.getPId()));// 不存在则添加到treeList2
			}
			if (checkNodeExist(findRootNode(tree.getPId()), treeList1)) {// 判断是否存在节点1
				treeList1.add(findRootNode(tree.getPId()));// 不存在则添加到treeList1
			}
		}
		// 根据treeList1、treeList2、treeList3拼装树
		for (TaskTree tree1 : treeList1) {
			list2 = new ArrayList<TaskTree>();
			for (TaskTree tree2 : treeList2) {
				if (tree1.getId().equals(tree2.getPId())) {
					list3 = new ArrayList<TaskTree>();
					for (TaskTree tree3 : treeList3) {
						if (tree2.getId().equals(tree3.getPId())) {
							list3.add(tree3);
						}
					}
					if (list3.size() > 0) {
						tree2.setChildren(list3);
						tree2.setState("closed");
					}
					tree2.setIconCls("icon-tree-level2");
					list2.add(tree2);
				}
			}
			if (list2.size() > 0) {
				tree1.setChildren(list2);
				tree1.setState("closed");
			}
			tree1.setIconCls("icon-tree-level1");
		}
		return treeList1;
	}

	public List<TaskTree> sortList(List<TaskTree> treeList) {
		for (int i = 0; i < treeList.size(); i++) {
			for (int j = 0; j < treeList.size() - 1 - i; j++) {
				TaskTree taskTree;
				if (treeList.get(j).getExpectStartTime().before(treeList.get(j + 1).getExpectStartTime())) {
					taskTree = treeList.get(j);
					treeList.set(j, treeList.get(j + 1));
					treeList.set(j + 1, taskTree);
				}
			}
		}
		// int i, j;
		// int n = treeList.size();
		// TaskTree target;
		// for (i = 1; i < n; i++) {
		// j = i;
		// target = treeList.get(i);
		// while (j > 0 && target.getExpectStartTime().after(treeList.get(j -
		// 1).getExpectStartTime())) {
		// treeList.set(j, treeList.get(j - 1));
		// j--;
		// }
		// treeList.set(j, target);
		// }
		return treeList;
	}

	private TaskTree findRootNode(String pId) {// 查找顶层节点
		TaskTree task = this.findPreNode(pId);
		if (task == null)
			return null;
		if (StringUtil.isNotEmpty(task.getPId())) {
			task = findRootNode(task.getPId());
		}
		return task;
	}

	private TaskTree findPreNode(String pId) {// 查找上层节点
		TaskTree task = taskTreeDao.findPreNode(pId);
		return task;
	}

	private Boolean checkNodeExist(TaskTree taskTree, List<TaskTree> treeList) {// 判断treeList中，是否存在taskTree节点
		boolean flag = true;
		if (taskTree == null)
			return false;
		for (TaskTree tree : treeList) {
			if (tree.getId().equals(taskTree.getId())) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Override
	public List<TaskTree> getTasksByNodeId(TaskDto taskDto) {
		int level = taskDto.getLevel() + 1;
		String pId = taskDto.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", level);
		map.put("pId", pId);
		List<TaskTree> treeList1 = taskTreeDao.getTasks(map);
		// for(TaskTree )

		return treeList1;
	}

	@Override
	public XSSFWorkbook exportTask(Map<String, Object> taskMap) throws Exception {
		XSSFWorkbook xssfWorkbook = null;
		List<TaskTree> treeList = new ArrayList<TaskTree>();
		try {
			treeList = sortList(createTaskTree(taskMap));
			int i = 0;
			int count = 0;
			int len = treeList.size();
			while (count < len) {
				if (treeList.get(i).getChildren().size() > 0) {
					treeList.addAll(i + 1, treeList.get(i).getChildren());
					i = i + treeList.get(i).getChildren().size() + 1;
				} else {
					i++;
				}
				count++;
			}
			// taskMap.put("level", 1);
			// List<TaskTree> list1 = taskTreeDao.getTasks(taskMap);//
			// 查询所有符合条件的一级节点
			// taskMap.put("level", 2);
			// List<TaskTree> list2 = taskTreeDao.getTasks(taskMap);//
			// 查询所有符合条件的二级节点
			// taskMap.put("level", 3);
			// List<TaskTree> list3 = taskTreeDao.getTasks(taskMap);//
			// 查询所有符合条件的三级节点
			// for (int i = 0; i < list1.size(); i++) {
			// TaskTree tree1 = list1.get(i);
			// List<TaskTree> treeList1 = new ArrayList<TaskTree>();
			// for (int j = 0; j < list2.size(); j++) {
			// List<TaskTree> treeList2 = new ArrayList<TaskTree>();
			// TaskTree tree2 = list2.get(j);
			// List<TaskTree> treeList3 = new ArrayList<TaskTree>();
			// for (int k = 0; k < list3.size(); k++) {
			// TaskTree tree3 = list3.get(k);
			// if (tree3.getPId().equals(tree2.getId())) {
			// treeList3.add(tree3);
			// list3.remove(k);
			// k--;
			// }
			// }
			// treeList2.add(tree2);
			// treeList2.addAll(treeList3);
			// if (tree2.getPId().equals(tree1.getId())) {
			// treeList1.addAll(treeList2);
			// list2.remove(j);
			// j--;
			// }
			// }
			// treeList.add(tree1);
			// treeList.addAll(treeList1);
			// }
			// treeList.addAll(list2);
			// treeList.addAll(list3);
			List<ExcelBean> excel = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
			// 设置标题栏
			excel.add(new ExcelBean("任务等级", "level", 0, false));
			excel.add(new ExcelBean("任务名称", "taskName", 0, false));
			excel.add(new ExcelBean("测试负责人", "executor", 0, false));
			excel.add(new ExcelBean("任务阶段", "testStage", 0, false));
			excel.add(new ExcelBean("预计开始时间", "expectStartTime", 0, false));
			excel.add(new ExcelBean("预计结束时间", "deadline", 0, false));
			excel.add(new ExcelBean("预计工时", "expectDay", 0, false));
			excel.add(new ExcelBean("计划进度(%)", "expectProgress", 0, false));
			excel.add(new ExcelBean("实际开始时间", "actualStartTime", 0, false));
			excel.add(new ExcelBean("实际结束时间", "actualEndTime", 0, false));
			excel.add(new ExcelBean("实际工时", "actualDay", 0, false));
			excel.add(new ExcelBean("实际进度(%)", "actualProgress", 0, false));
			excel.add(new ExcelBean("工作量", "workload", 0, false));
			excel.add(new ExcelBean("项目系数", "workratio", 0, false));
			excel.add(new ExcelBean("当前状态", "taskState", 0, false));
			excel.add(new ExcelBean("编写用例天数(天)", "caseDay", 0, false));
			excel.add(new ExcelBean("编写用例数", "caseNum", 0, false));
			map.put(0, excel);
			String sheetName = "任务信息";
			xssfWorkbook = ExcelUtil.createExcelFile(TaskTree.class, treeList, map, sheetName);
		} catch (Exception e) {
			throw e;
		}
		return xssfWorkbook;
	}

	@Override
	public XSSFWorkbook exportWorkData(Map<String, Object> taskMap) throws Exception {
		XSSFWorkbook xssfWorkbook = null;
		List<Task> taskList = taskDao.getWorkData(taskMap);
		try {
			List<ExcelBean> excel = new ArrayList<>();
			Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
			// 设置标题栏
			excel.add(new ExcelBean("任务名称", "taskName", 0, false));
			excel.add(new ExcelBean("测试负责人", "executor", 0, false));
			excel.add(new ExcelBean("任务阶段", "testStage", 0, false));
			excel.add(new ExcelBean("预计开始时间", "expectStartTime", 0, false));
			excel.add(new ExcelBean("预计结束时间", "deadline", 0, false));
			excel.add(new ExcelBean("预计工时", "expectDay", 0, false));
			excel.add(new ExcelBean("计划进度(%)", "expectProgress", 0, false));
			excel.add(new ExcelBean("实际开始时间", "actualStartTime", 0, false));
			excel.add(new ExcelBean("实际结束时间", "actualEndTime", 0, false));
			excel.add(new ExcelBean("实际工时", "actualDay", 0, false));
			excel.add(new ExcelBean("实际进度(%)", "actualProgress", 0, false));
			excel.add(new ExcelBean("工作量", "workload", 0, false));
			excel.add(new ExcelBean("项目系数", "workratio", 0, false));
			excel.add(new ExcelBean("当前状态", "taskState", 0, false));
			excel.add(new ExcelBean("编写用例天数(天)", "caseDay", 0, false));
			excel.add(new ExcelBean("编写用例数", "caseNum", 0, false));
			map.put(0, excel);
			String sheetName = "任务信息";
			xssfWorkbook = ExcelUtil.createExcelFile(Task.class, taskList, map, sheetName);
		} catch (Exception e) {
			throw e;
		}
		return xssfWorkbook;
	}

	@Override
	public int countNode(String id) {
		return taskTreeDao.countNode(id);
	}

	@Override
	public int countAllTreeNum(Map<String, Object> map) {
		return taskTreeDao.countAllTreeNum(map);
	}

	@Override
	public List<Task> getSubTask(String id) {
		return taskDao.getSubTask(id);
	}

	@Override
	public boolean checkRepeat(Map<String, Object> map) {
		return taskDao.checkRepeat(map);
	}

	@Override
	public void updateHistory(TaskHistoryVo taskHistoryVo) {
		taskDao.updateHistory(taskHistoryVo);
	}

	@Override
	public void addHistory(TaskHistoryVo taskHistoryVo) {
		taskDao.addHistory(taskHistoryVo);
	}

	@Override
	public Integer countWorkLoad(String taskId) {
		return taskDao.countWorkLoad(taskId);
	}

	@Override
	public List<TaskHistory> selectHistory(String taskId) {
		return taskDao.selectHistory(taskId);
	}

	@Override
	public Double maxProgress(String taskId) {
		return taskDao.maxProgress(taskId);
	}

	@Override
	public List<Task> getTodayTask(Map<String, Object> map) {
		return taskDao.getTodayTask(map);
	}

	@Override
	public List<Task> getWorkData(Map<String, Object> map) {
		return taskDao.getWorkData(map);
	}

	@Override
	public void deleteHistory(String id) {
		taskDao.deleteHistory(id);
	}

}

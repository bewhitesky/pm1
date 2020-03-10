var active;
layui.use('element', function(){
  var $ = jQuery = layui.jquery;
  var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
  var layer = layui.layer;
  active = {
	tabAdd : function(url, id,name) {
		// 新增一个Tab项
		element.tabAdd('content', {
			title : name// 用于演示
			,
			content : '<iframe data-frameid="'
					+ id
					+ '" frameborder="0" name="content" scrolling="auto"  width="100%" src="'
					+ url + '"></iframe>',
			id : id
				// 实际使用一般是规定好的id，这里以时间戳模拟下
		})
//		CustomRightClick(id);// 绑定右键菜单
		FrameWH();// 计算框架高度
	},
	tabChange : function(id) {
		// 切换到指定Tab项
		element.tabChange('content', id); // 切换到：用户管理
		$("iframe[data-frameid='" + id + "']").attr("src",
				$("iframe[data-frameid='" + id + "']").attr("src"));// 切换后刷新框架
		
	},
	tabDelete : function(id) {
		element.tabDelete("content", id);// 删除
	},
	tabDeleteAll : function(ids) {// 删除所有
		$.each(ids, function(i, item) {
					element.tabDelete("content", item);
				})
	},
	tabRefresh:function(id){
		$("iframe[data-frameid='" + id + "']").width(1700);
		$("iframe[data-frameid='" + id + "']").attr("src",
				$("iframe[data-frameid='" + id + "']").attr("src"))// 切换后刷新框架
	}
};

$(".layui-side-scroll a").click(function() {
			var dataid = $(this);
			if(dataid.attr("data-url")==null)return;
			if ($(".layui-tab-title li[lay-id]").length <= 0) {
				active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-name"));
			} else {
				var isData = false;
				$.each($(".layui-tab-title li[lay-id]"), function() {
							if ($(this).attr("lay-id") == dataid
									.attr("data-id")) {
								isData = true;
							}
						})
				if (isData == false) {
					active.tabAdd(dataid.attr("data-url"), dataid
									.attr("data-id"),dataid.attr("data-name"));
				}
			}
			active.tabChange(dataid.attr("data-id"));
		})

//绑定右键菜单
function CustomRightClick(id) {
                // 取消右键 rightmenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
		$('.layui-tab-title li').on('contextmenu', function() {
					return false;
				})
		$('.layui-tab-title,.layui-tab-title li').click(function() {
					$('.rightmenu').hide();
				});
		// 桌面点击右击
		$('.layui-tab-title li').on('contextmenu', function(e) {
			var popupmenu = $(".rightmenu");
			popupmenu.find("li").attr("data-id", id); // 在右键菜单中的标签绑定id属性

			// 判断右侧菜单的位置
			l = ($(document).width() - e.clientX) < popupmenu.width()
					? (e.clientX - popupmenu.width())
					: e.clientX;
			t = ($(document).height() - e.clientY) < popupmenu.height()
					? (e.clientY - popupmenu.height())
					: e.clientY;
			popupmenu.css({
						left : l,
						top : t
					}).show(); // 进行绝对定位
			// alert("右键菜单")
			return false;
		});
}
$(".rightmenu li").click(function () {
            if ($(this).attr("data-type") == "closethis") {
                active.tabDelete($(this).attr("data-id"))
            } else if ($(this).attr("data-type") == "closeall") {
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                })
 
                active.tabDeleteAll(ids);
            }
 
            $('.rightmenu').hide();
        })
        
 function FrameWH() {
            var h = $(window).height() -41- 10 - 30 -10-44 -4+11;
            $("iframe").css("height",h+"px");
        }
 
        $(window).resize(function () {
            FrameWH();
        })

});

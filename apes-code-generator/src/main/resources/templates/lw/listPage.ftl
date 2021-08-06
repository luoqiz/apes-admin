<%@ include file="/WEB-INF/comm/comm.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %> 
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<head>
<meta  charset="UTF-8">
<title>菜单列表页面</title>
<style type="text/css">
body{
width:100%;height:100%;}
</style>
</head>
<body>
<div class="container-full">
        <div id="toolbar">
            <button id="itemAdd" class="btn btn-success">
                <i class="glyphicon glyphicon-plus"></i> 新增
            </button>
            <button id="item-remove" class="btn btn-danger" disabled>
                <i class="glyphicon glyphicon-remove"></i> 删除
            </button>
            </div>
        <table id="table" data-toolbar="#toolbar" style="table-layout: fixed;word-break:break-all; word-wrap:break-all;" >

        </table>
    </div>
    <script type="text/javascript">
    
    var $table = $('#table'), itemSelections = [];
    var $itemRemove = $('#item-remove');
    layui.define([ 'layer', 'form' ], function(exports) {
        var layer = layui.layer, form = layui.form();

        $(function() {
            initTable();
            // FixTable("table", 2, 2000, 2000);
        });
            $itemRemove.click(function() {
            var ids = getIdSelections();
            // eg2",
            layer.open({
              content: '确认要删除标识为['+ids+']的数据'
              ,btn: ['删除', '取消']
              ,yes: function(index, layero){
                // 按钮【按钮一】的回调
                  $.ajax({
                      url : "sysMenu/batchDeleteByPrimaryKey.do",    // 请求的url地址
                        dataType: "json",   // 返回格式为json
                        async: true, // 请求是否异步，默认为异步，这也是ajax重要特性
                        data: { "ids": ids.join(",") },    // 参数值
                        type: "post",   // 请求方式
                        success: function(req) {
                             if(req.success){
                                 $table.bootstrapTable('remove', {
                                        field : 'id',
                                        values : ids
                                    });
                                  $itemRemove.prop('disabled', true);
                                  layer.msg(req.msg, {
                                      offset: 'rb',
                                      anim: 6,
                                      icon: 6,
                                      area:[100,100]
                                    });
                             }else{
                                 layer.msg(req.msg, {
                                      offset: 'rb',
                                      anim: 6,
                                      icon: 5,
                                      area:[100,100]
                                    });
                             }
                        },
                        complete: function() {
                            // 请求完成的处理
                            layer.close(index);
                        },
                        error: function() {
                            // 请求出错处理
                        }
                    });

              }
              ,btn2: function(index, layero){
                // 按钮【按钮二】的回调
                  layer.close(index);
                // return false 开启该代码可禁止点击该按钮关闭
              }
            });
        });
        
        $("#itemAdd").click(function() { 
            layer.open({ type : 2,title:'新增',
            maxmin : true,
            area : [ '80%', '80%' ], 
            content :    '${r'${'}pageContext.request.contextPath}/sysMenu/getSysMenuEditPageForSingleRow.do'  // 这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:
                                                            // ['http://sentsin.com','no']
            ,end : function(){
                      $table.bootstrapTable("refresh",$table.bootstrapTable("getOptions","url"));
                  } 
            });
        });
        exports('menuList', {}); // 注意，这里是模块输出的核心，模块名必须和use时的模块名一致
    });

    function initTable() {
        $table.bootstrapTable({
            height : getHeight(),
            url : "${pojoName?uncap_first}/list${pojoName}Condition.do", // 服务器数据的加载地址
            showToggle : true, // 是否显示 切换试图（table/card）按钮
            showColumns : true, // 是否显示 内容列下拉框
            search : true,  // 是否启用搜索框
            showRefresh : true, // 是否显示 刷新按钮
            pageNumber:1,      // 初始化加载第一页，默认第一页
            pageSize: 10,      // 每页的记录行数（*）
            pageList : "20, 50, 100, ALL",
            showFooter : false, // 是否显示列脚
            sidePagination : "clien" , // 设置在哪里进行分页，可选值为 'client' 或者 'server'。设置
                                    // 'server'时，必须设置 服务器数据地址（url）或者重写ajax方法
            responseHandler: responseHandler,  // 加载服务器数据之前的处理程序，可以用来格式化数据。
                                                // 参数：res为从服务器请求到的数据。
            detailView : true, // 设置为 true 可以显示详细页面模式。
            detailFormatter : detailFormatter, // 格式化详细页面模式的视图。
            showPaginationSwitch : true, // 是否显示 数据条数选择框
            minimumCountColumns :2, // 当列数小于此值时，将隐藏内容列下拉框。
            pagination : true,
            showHeader : true, // 是否显示列头
            idField : "${javaTablePK}",// 指定主键列
            showExport : true ,
            clickToSelect: true,    //是否启用点击选中行
            searchOnEnterKey: true,
            queryParamsType:'', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
            // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
            queryParams : function(params){
                return {"pageSize":params.pageSize, "pageNumber":params.pageNumber,
                    "searchText":params.searchText, "sortName":params.sortName,
                    "sortOrder":params. sortOrder   
                };
            },
            paginationPreText: '上一页',       //上一页按钮里text  
            paginationNextText: '下一页',       //下一页按钮里text  
            columns : [ {    // 列配置项,详情请查看 列参数 表格.
                field : 'state',
                title : '是否选中',
                checkbox : true,
                align : 'center',
                valign : 'middle',
                width : 40
            }, 
           <#list arrayList as row>
            <#if javaTablePK==row["javaColumnName"]>
            {
                field : '${row["javaColumnName"]}',
                title : '${row["comment"]}',
                //sortable : true,
                align : 'center',
                valign : 'middle',
                width : 120,
                visible : false
            },
            <#else>
            {
                field : '${row["javaColumnName"]}',
                title : '${row["comment"]}',
                //sortable : true,
                align : 'center',
                valign : 'middle',
                width : 120,
                formatter : function(value){
                        return value;
                }
            },
            </#if>
        </#list>
          {
                title : '操作',
                align : 'center',
                width : 120,
                events: operateEvents,
                formatter: operateFormatter
            }],
            onClickRow : function(row, tr) {
                // 进行你的操作，如弹出新窗口
                // alert($(tr).css("backgroundColor"));
                if ($(tr).find("td:eq(3)").css("background-color") == "rgb(237, 252, 183)") {
                    $(tr).find("td").css("background-color", "");
                } else {
                    $(tr).find("td").css('background-color', "rgb(237, 252, 183)");
                }
            }

        });
        
        // sometimes footer render error.
        setTimeout(function() {
            $table.bootstrapTable('resetView');
        }, 200);

        // 点击复选框时触发
        $table.on('check.bs.table uncheck.bs.table ' + 'check-all.bs.table   uncheck-all.bs.table', function() {
            $itemRemove.prop('disabled', !$table.bootstrapTable('getSelections').length);
            // save your data, here just save the current page
            itemSelections = getIdSelections();
            // alert(selections);
            // push or splice the selections if you want to save all data selections
        });
        // 点击第一列的展开
        $table.on('expand-row.bs.table', function(e, index, row, $detail) {
            /*
             * if (index % 2 == 1) { $detail.html('Loading from ajax request...');
             * $.get('LICENSE', function(res) { $detail.html(res.replace(/\n/g, '<br>'));
             * }); }
             */
        });
        $table.on('all.bs.table', function(e, name, args) {
            console.log(name, args);
        });

        $(window).resize(function() {
            $table.bootstrapTable('resetView', {
                height : getHeight()
            });
        });

        // 编辑框
        function operateFormatter(value, row, index) {
            return [ '<a class="edit" href="javascript:void(0)" title="edit">', '<i class="glyphicon glyphicon-pencil"></i>', '</a> ',
                    '<a class="remove" href="javascript:void(0)" title="Remove">', '<i  class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');
        }
        
    }

     window.operateEvents = {
                'click .edit': function (e, value, row, index) {
                  //  alert('You click like action, row: ' + JSON.stringify(row));
                        layer.open({ type : 2,
                            title:"更新",
                            maxmin : true,
                            area : [ '80%', '80%' ], 
                            content : '${r'${'}pageContext.request.contextPath}/sysMenu/getSysMenuEditPageForSingleRow.do?id=' +row.id//+ JSON.stringify(row) // 这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:
                    ,
                    end : function() {
                        $table.bootstrapTable("refresh", $table.bootstrapTable(
                                "getOptions", "url"));
                    } // ['http://sentsin.com','no']
                });
            },
            'click .remove' : function(e, value, row, index) {
                var url="${r'${'}pageContext.request.contextPath}/sysMenu/deleteByPrimaryKey.do";
                var data={"id":row.id};
                $.post(url,data,function(req){
                    var msg=req.msg;
                    var statNum=5;
                    if(req.success){
                        if(msg==""||msg==null){
                            msg="删除成功";
                            statNum=6;
                        }
                        $table.bootstrapTable('remove', {
                            field : 'id',
                            values : [ row.id ]
                        });
                    }else{
                        if(msg==""||msg==null){
                            msg="删除失败";
                        }
                    }
                    layer.msg(msg, {
                          offset: 'rb',
                          anim: 6,
                          icon: statNum,
                          area:[100,100]
                        });
                },'json');
                
            }
        };

        // 获取选中的行
        function getIdSelections() {
            return $.map($table.bootstrapTable('getSelections'), function(row) {
                return row.id
            });
        }
        //日期格式化
        function dateFormatter(value) {
            return value.split(" ")[0];
        }
        //细节格式化显示
        function detailFormatter(index, row) {
            var html = [];
            var tableColumns = $table.bootstrapTable('getOptions').columns[0];
            $.each(tableColumns, function(index1) {
                html.push('<p><b>' + tableColumns[index1].title + ':</b> '
                        + row[tableColumns[index1].field] + '</p>');
            })
            return html.join('');
        }
        //服务器返回的内容处理
        function responseHandler(res) {
            //console.log(res.obj);
            $.each(res.obj, function(i, row) {
                row.state = $.inArray(row.id, itemSelections) !== -1;
            });
            return res.obj;
        }

        // 布尔值转换
        function booleanFormatter(value) {
            // console.info(value);
            if (!value) {
            //  return '√';
            }else{
                return '√';
            }
        }

        // 获取高度
        function getHeight() {
            return $(window).height() - 50; // $('h1').outerHeight(true);
        }
    </script>
</body>
</html>
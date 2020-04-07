<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic Tabs - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="../easyui1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../easyui1.7.0/themes/demo.css">
    <script type="text/javascript" src="../easyui1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui1.7.0/jquery.easyui.min.js"></script>

    <script>
        //为datagrid定义 editCell方法
        $.extend($.fn.datagrid.methods, {
            editCell: function (jq, param) {
                return jq.each(function () {
                    var opts = $(this).datagrid('options');
                    var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor1 = col.editor;
                        if (fields[i] != param.field) {
                            col.editor = null;
                        }
                    }
                    $(this).datagrid('beginEdit', param.index);
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor = col.editor1;
                    }
                });
            }
        });

        var editIndex = undefined;

        function endEditingHard() {
            if (editIndex == undefined) {
                return true
            }
            if ($('#hardware').datagrid('validateRow', editIndex)) {
                $('#hardware').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }

        function endEditingSoft() {
            if (editIndex == undefined) {
                return true
            }
            if ($('#software').datagrid('validateRow', editIndex)) {
                $('#software').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }

        function endEditingDocument() {
            if (editIndex == undefined) {
                return true
            }
            if ($('#document').datagrid('validateRow', editIndex)) {
                $('#document').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }

        function onClickCellHard(index, field) {
            if (endEditingHard()) {
                $('#hardware').datagrid('selectRow', index).datagrid('editCell', {index: index, field: field});
                var ed = $('#hardware').datagrid('getEditor', {index: index, field: field});//获取当前编辑器
                if (ed != null)
                    $(ed.target).focus();//获取焦点

                //$(ed.target).blur();
                editIndex = index;
            }
        }

        function onClickCellSoft(index, field) {
            if (endEditingSoft()) {
                $('#software').datagrid('selectRow', index).datagrid('editCell', {index: index, field: field});
                var ed = $('#software').datagrid('getEditor', {index: index, field: field});//获取当前编辑器
                if (ed != null)
                    $(ed.target).focus();//获取焦点

                //$(ed.target).blur();
                editIndex = index;
            }
        }

        function onClickCellDocument(index, field) {
            if (endEditingDocument()) {
                $('#document').datagrid('selectRow', index).datagrid('editCell', {index: index, field: field});
                var ed = $('#document').datagrid('getEditor', {index: index, field: field});//获取当前编辑器
                if (ed != null)
                    $(ed.target).focus();//获取焦点
                //$(ed.target).blur();
                editIndex = index;
            }
        }

        function formatterHard(val, row, index) {
            if (row.id != null && row.id != '')
                return '<a href="#" onclick="modifyHard(' + JSON.stringify(row).replace(/\"/g, "'") + ')">&nbsp修改</a>&nbsp&nbsp&nbsp<a href="#" onclick="deleteRowHard(' + row.id + ')">删除</a>'
            else
                return '<a href="#" onclick="insertHard(' + JSON.stringify(row).replace(/\"/g, "'") + ')">添加</a>'
        }

        function formatterSoft(val, row, index) {
            if (row.id != null && row.id != '')
                return '<a href="#" onclick="modifySoft(' + JSON.stringify(row).replace(/\"/g, "'") + ')">&nbsp修改</a>&nbsp&nbsp&nbsp<a href="#" onclick="deleteRowSoft(' + row.id + ')">删除</a>'
            else
                return '<a href="#" onclick="insertSoft(' + JSON.stringify(row).replace(/\"/g, "'") + ')">添加</a>'
        }

        function formatterDocument(val, row, index) {
            if (row.id != null && row.id != '')
                return '<a href="#" onclick="modifyDocument(' + JSON.stringify(row).replace(/\"/g, "'") + ')">&nbsp修改</a>&nbsp&nbsp&nbsp<a href="#" onclick="deleteRowDocument(' + row.id + ')">删除</a>'
            else
                return '<a href="#" onclick="insertDocument(' + JSON.stringify(row).replace(/\"/g, "'") + ')">添加</a>'
        }

        function exportByQrId(row) {
            var tab = $('#tabs').tabs('getSelected');
            var index = $('#tabs').tabs('getTabIndex', tab);
            if (index == 0) {
                row.qrid = 'h' + row.id;
                window.location.href = '/hardware/export?jsonObject=' + encodeURIComponent(JSON.stringify(row))
            } else if (index == 1) {
                row.qrid = 's' + row.id;
                window.location.href = '/software/export?jsonObject=' + encodeURIComponent(JSON.stringify(row))
            } else if (index == 2) {
                row.qrid = 'd' + row.id;
                window.location.href = '/document/export?jsonObject=' + encodeURIComponent(JSON.stringify(row))
            }

        };

        function exportQr(val, row, index) {
            return '<a href="#" class="easyui-linkbutton" onclick="exportByQrId(' + JSON.stringify(row).replace(/\"/g, "'") + ')" style="" data-options="iconCls:icon-search">导出</a>'
        }


        function addHard() {
            $('#hardware').datagrid('insertRow', {
                row: {
                    id: '',
                    name: '',
                    factory: '',
                    support: '',
                    buytime: '',
                    price: '',
                    instruction: '',
                    record: '',
                    intime: '',
                    qrtime: '',
                    qrid: ''
                }
            });
        }

        function addSoft() {
            $('#software').datagrid('insertRow', {
                row: {
                    id: '',
                    name: '',
                    version: '',
                    publishtime: '',
                    functions: '',
                    relation: '',
                    location: '',
                    instruction: '',
                    qrid: ''
                }
            });
        }

        function addDocument() {
            $('#document').datagrid('insertRow', {
                row: {
                    id: '',
                    name: '',
                    version: '',
                    createtime: '',
                    modifytime: '',
                    publish: '',
                    application: '',
                    secret: '',
                    cipher: '',
                    location: '',
                    qrid: ''
                }
            });
        }


        function insertHard(row) {
            $.ajax({
                url: '/hardware/in',
                data: row,
                success: function (data) {
                    if (data == 'success') {
                        $.messager.alert('信息提示', '修改成功！', 'info');
                        $('#hardware').datagrid('reload');
                    } else {
                        $.messager.alert('信息提示', data, 'warning');
                    }
                }
            });
        }

        function insertSoft(row) {
            $.ajax({
                url: '/software/add',
                data: row,
                success: function (data) {
                    if (data == 'success') {
                        $.messager.alert('信息提示', '修改成功！', 'info');
                        $('#software').datagrid('reload');
                    } else {
                        $.messager.alert('信息提示', data, 'warning');
                    }
                }
            });
        }

        function insertDocument(row) {
            $.ajax({
                url: '/document/add',
                data: row,
                success: function (data) {
                    if (data == 'success') {
                        $.messager.alert('信息提示', '修改成功！', 'info');
                        $('#document').datagrid('reload');
                    } else {
                        $.messager.alert('信息提示', data, 'warning');
                    }
                }
            });
        }

        function modifyHard(row) {
            /*  var selected = $("#hardware").datagrid('getSelected');
              if (selected) {
                  var index = $('#hardware').datagrid('getRowIndex', selected);
                  alert(index)
                  $('#hardware').datagrid('endEdit', index)
                  $('#hardware').datagrid('endEdit', index+1)
              }*/

            if (confirm("确定修改吗？")) {
                $.ajax({
                    url: '/hardware/modify',
                    //dataType:'json',
                    // type: 'post',
                    //不加contentType:"application/json"，要传json对象，加了要传json字符串，如果传json对象就不需要encodeURICom这个方法转义了
                    data: row,
                    success: function (data) {
                        if (data == 'success') {
                            $.messager.alert('信息提示', '修改成功！', 'info');
                            $('#hardware').datagrid('reload');
                        } else {
                            $.messager.alert('信息提示', data, 'warning');
                        }
                    }
                });
            }
        }

        function modifySoft(row) {
            if (confirm("确定修改吗？")) {
                $.ajax({
                    url: '/software/modify',
                    data: row,
                    success: function (data) {
                        if (data == 'success') {
                            $.messager.alert('信息提示', '修改成功！', 'info');
                            $('#software').datagrid('reload');
                        } else {
                            $.messager.alert('信息提示', data, 'warning');
                        }
                    }
                });
            }
        }

        function modifyDocument(row) {
            if (confirm("确定修改吗？")) {
                $.ajax({
                    url: '/document/modify',
                    data: row,
                    success: function (data) {
                        if (data == 'success') {
                            $.messager.alert('信息提示', '修改成功！', 'info');
                            $('#document').datagrid('reload');
                        } else {
                            $.messager.alert('信息提示', data, 'warning');
                        }
                    }
                });
            }
        }

        function deleteRowHard(id) {
            if (confirm("确定删除吗？")) {
                $.ajax({
                    url: '/hardware/out',
                    //dataType:'json',
                    // type: 'post',
                    data: {id: id},
                    success: function (data) {
                        if (data == 'success') {
                            $.messager.alert('信息提示', '删除成功！', 'info');
                            $('#hardware').datagrid('reload');
                        } else {
                            $.messager.alert('信息提示', data, 'warning');
                        }
                    }
                });
            }
        }

        function deleteRowSoft(id) {
            if (confirm("确定删除吗？")) {
                $.ajax({
                    url: '/software/delete',
                    data: {id: id},
                    success: function (data) {
                        if (data == 'success') {
                            $.messager.alert('信息提示', '删除成功！', 'info');
                            $('#software').datagrid('reload');
                        } else {
                            $.messager.alert('信息提示', data, 'warning');
                        }
                    }
                });
            }
        }

        function deleteRowDocument(id) {
            if (confirm("确定删除吗？")) {
                $.ajax({
                    url: '/document/delete',
                    data: {id: id},
                    success: function (data) {
                        $.messager.alert('信息提示', '删除成功！', 'info');
                        $('#document').datagrid('reload');
                    }
                });
            }
        }

        function queryHard() {
            $.ajax({
                url: '/hardware/search',
                data: {name: $('#txt_hardName').val()},
                dataType: 'json',
                success: function (data) {
                    $('#hardware').datagrid('loadData', data);
                },
                error: function (e) {
                    alert(e);
                }
            });
        }

        function querySoft() {
            $.ajax({
                url: '/software/find',
                data: {name: $('#txt_softName').val()},
                dataType: 'json',
                success: function (data) {
                    $('#software').datagrid('loadData', data);
                },
                error: function (e) {
                    alert(e);
                }
            });
        }

        function queryDocument() {
            $.ajax({
                url: '/document/find',
                data: {name: $('#txt_documentName').val()},
                dataType: 'json',
                success: function (data) {
                    $('#document').datagrid('loadData', data);
                },
                error: function (e) {
                    alert(e);
                }
            });
        }


    </script>
</head>
<body>
<div class="easyui-tabs" id="tabs" style="width:1200px;height:450px;margin-left: 200px">
    <div title="Hardware" style="">
        <input class="easyui-textbox" id="txt_hardName" data-options="prompt:'输入name关键字',validType:'text',align:'right'"
               style="margin: 40px;width:150px;height:32px;">
        <a href="#" class="easyui-linkbutton" onclick="queryHard()" style="margin:5px;margin-left: 7%; "
           data-options="iconCls:'icon-search'">查询</a>
        <table class="easyui-datagrid" id="hardware" title="" style=""
               data-options="singleSelect:true,fitColumns:true,url:'/hardware/search',method:'get',onClickCell: onClickCellHard">
            <thead>
            <tr>
                <th data-options="field:'id',width:60,align:'center',editor:'text',hidden: true">id</th>
                <th data-options="field:'name',width:60,align:'center',editor:'text'">name</th>
                <th data-options="field:'factory',width:60,align:'center',editor:'text'">factory</th>
                <th data-options="field:'support',width:60,align:'center',editor:'text'">support</th>
                <th data-options="field:'buytime',width:60,align:'center',editor:'text'">buytime</th>
                <th data-options="field:'price',width:60,align:'center',editor:'text'">price</th>
                <th data-options="field:'instruction',width:60,align:'center',editor:'text'">instruction</th>
                <th data-options="field:'record',width:60,align:'center',editor:'text'">record</th>
                <th data-options="field:'intime',width:60,align:'center',editor:'text'">intime</th>
                <th data-options="field:'qrid',width:60,align:'center', formatter:exportQr">qrid</th>
                <th data-options="field:'add',align:'center', formatter:formatterHard">
                    <a href="#" class="easyui-linkbutton" onclick="addHard()" data-options="iconCls:'icon-add'">添加</a>
                </th>
            </tr>
            </thead>
        </table>
    </div>

    <div title="Software" style="">

        <input class="easyui-textbox" id="txt_softName" data-options="prompt:'输入name关键字',validType:'text',align:'right'"
               style="margin: 40px;width:150px;height:32px;">
        <a href="#" class="easyui-linkbutton" onclick="querySoft()" style="margin:5px;margin-left: 7%; "
           data-options="iconCls:'icon-search'">查询</a>
        <table class="easyui-datagrid" id="software" title="" style=""
               data-options="singleSelect:true,fitColumns:true,url:'/software/find',method:'get',onClickCell: onClickCellSoft">
            <thead>
            <tr>
                <th data-options="field:'id',width:60,align:'center',editor:'text',hidden: true">id</th>
                <th data-options="field:'name',width:60,align:'center',editor:'text'">name</th>
                <th data-options="field:'version',width:60,align:'center',editor:'text'">version</th>
                <th data-options="field:'publishtime',width:60,align:'center',editor:'text'">publishtime</th>
                <th data-options="field:'functions',width:60,align:'center',editor:'text'">functions</th>
                <th data-options="field:'relation',width:60,align:'center',editor:'text'">relation</th>
                <th data-options="field:'location',width:60,align:'center',editor:'text'">location</th>
                <th data-options="field:'instruction',width:60,align:'center',editor:'text'">instruction</th>
                <th data-options="field:'qrid',width:60,align:'center',formatter:exportQr">qrid</th>
                <th data-options="field:'add',align:'center',formatter:formatterSoft">
                    <%-- <a href="#" onclick="addSoft()">添加</a>--%>
                    <a href="#" class="easyui-linkbutton" onclick="addSoft()" data-options="iconCls:'icon-add'">添加</a>
                </th>
            </tr>
            </thead>
        </table>
    </div>

    <div title="Document" style="padding:0px">
        <input class="easyui-textbox" id="txt_documentName"
               data-options="prompt:'输入name关键字',validType:'text',align:'right'"
               style="margin: 40px;width:150px;height:32px;">
        <a href="#" class="easyui-linkbutton" onclick="queryDocument()" style="margin:5px;margin-left: 7%; "
           data-options="iconCls:'icon-search'">查询</a>
        <table class="easyui-datagrid" id="document" title="" style=""
               data-options="singleSelect:true,fitColumns:true,url:'/document/find',method:'get',onClickCell: onClickCellDocument">
            <thead>
            <tr>
                <th data-options="field:'id',width:60,align:'center',editor:'text',hidden: true">id</th>
                <th data-options="field:'name',width:60,align:'center',editor:'text'">name</th>
                <th data-options="field:'version',width:60,align:'center',editor:'text'">version</th>
                <th data-options="field:'createtime',width:60,align:'center',editor:'text'">createtime</th>
                <th data-options="field:'modifytime',width:60,align:'center',editor:'text'">modifytime</th>
                <th data-options="field:'publish',width:60,align:'center',editor:'text'">publish</th>
                <th data-options="field:'application',width:60,align:'center',editor:'text'">application</th>
                <th data-options="field:'secret',width:60,align:'center',editor:'text'">secret</th>
                <th data-options="field:'cipher',width:60,align:'center',editor:'text'">cipher</th>
                <th data-options="field:'location',width:60,align:'center',editor:'text'">location</th>
                <th data-options="field:'qrid',width:60,align:'center',formatter:exportQr">qrid</th>
                <th data-options="field:'add',align:'center',formatter:formatterDocument">
                    <%--<a href="#" onclick="addDocument()">添加</a>--%>
                    <a href="#" class="easyui-linkbutton" onclick="addDocument()"
                       data-options="iconCls:'icon-add'">添加</a>
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</div>

</body>
</html>

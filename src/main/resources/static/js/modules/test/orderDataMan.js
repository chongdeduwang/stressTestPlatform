$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/manage/list',
        datatype: "json",
        colModel: [
            {label: '数据ID', name: 'id', width: 50, key: true},
            {label: '名称', name: 'dataName', sortable: false, width: 150},
            {label: '添加时间', name: 'addTime', width: 90},
            // {label: '项目', name: 'project', sortable: false, width: 80},
            {label: '内容', name: 'content', sortable: false, width: 80},
            {label: '模板', name: 'templateName', sortable: true, width: 60},
            // { label: 'cron表达式 ', name: 'cronExpression', width: 100 },
            {label: '备注', name: 'remark', sortable: false, width: 110}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 50,
        rowList: [10, 30, 50, 100, 200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        loadComplete: function (data) {
            console.log(data);
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            dataName: null
        },
        showList: true,
        showEdit: false,
        showOrderModes: false,
        showOrderEdit: false,
        orderContent: {},
        title: null,
        dataInstance: {},
        modes: 'pick',
        orderModes: 1,
        text: '',
        controlName: [],
        inputs: [],

    },
    methods: {
        getModeVal: function (event) {
            vm.modes = event.target.value;
            vm.showOrderModes = true;
        },
        getSendModeVal: function (event) {
            vm.orderModes = event.target.value;
            $.ajax({
                type: "GET",
                url: baseURL + "/template/manage/" + vm.modes + "/" + vm.orderModes,
                success: function (r) {
                    if (r.code == 0) {
                        vm.showOrderEdit = true;
                        vm.orderContent = r.template.content;
                        var jsonContent = JSON.parse(vm.orderContent);

                        var text = '';
                        for (var key in jsonContent) {




                            console.log(key + "      11111");
                            console.log(jsonContent[key] + "      22222");



                            // vm.inputs.push({
                            //     key,
                            //     placeholder: 'test',
                            //     label: 'this is label',
                            // });
                            if (!(jsonContent[key] instanceof Object)) {
                                if (jsonContent[key].search("input") !== -1 || jsonContent[key].search("long") !== -1) {


                                    var str = jsonContent[key];
                                    var pattern = new RegExp(".*?(?=\\()");
                                    var placeHolder = str.match(pattern);
                                    vm.controlName.push(key);

                                    vm.inputs.push({
                                        key,
                                        type:"input",
                                        placeholder: placeHolder,
                                        label: placeHolder
                                    });

                                    // text = text + "<div class=\"form-group\">\n" +
                                    //     "                    <div class=\"col-sm-10\">\n" +
                                    //     "                        <div class=\"col-sm-2 control-label\">" + key + "</div>\n" +
                                    //     "                        <input type=\"text\" class=\"form-control\" name='"+key+"' placeholder=\"" + placeHolder + "\"/>\n" +
                                    //     "                    </div>\n" +
                                    //     "                </div>"
                                }else if (jsonContent[key].search("checkbox")!==-1){
                                    var str = jsonContent[key];
                                    var pattern = new RegExp(".*?(?=\\()");
                                    var label = str.match(pattern);
                                    vm.inputs.push({
                                        key,
                                        type:"checkbox",
                                        checked: key,
                                        label: label
                                    })
                                }
                            }else {

                            }


                        }
                        vm.text = text;
                        console.log(vm.dataInstance);


                        console.log(r.template.content);
                    } else {
                        alert(r.msg);
                    }
                }
            });
            if (vm.orderContent != null && vm.orderContent !== '') {
                for (var key in vm.orderContent) {

                }
            }
        },
        query: function () {
            if (vm.q.dataName != null) {
                vm.reload();
            }
        },
        add: function () {
            vm.showList = false;
            vm.showEdit = true;
            vm.title = "新增";
            vm.dataInstance = {};
        },
        update: function () {
            var dataId = getSelectedRow();
            if (dataId == null) {
                return;
            }

            $.get(baseURL + "order/manage/info/" + dataId, function (r) {
                vm.showList = false;
                vm.showEdit = true;
                vm.title = "修改";
                vm.dataInstance = r.dataInfo;
            });
        },
        saveOrUpdate: function () {
            // for (var a in vm.controlName)

            console.log(JSON.stringify(vm.dataInstance)+"formJson");
            // if (vm.validator()) {
            //     return;
            // }
            //
            // var url = vm.dataInstance.id == null ? "test/stress/save" : "test/stress/update";
            // $.ajax({
            //     type: "POST",
            //     url: baseURL + url,
            //     contentType: "application/json",
            //     data: JSON.stringify(vm.dataInstance),
            //     success: function (r) {
            //         if (r.code === 0) {
            //             // alert('操作成功', function(){
            //             vm.reload();
            //             // });
            //         } else {
            //             alert(r.msg);
            //         }
            //     }
            // });
        },
        del: function () {
            var dataIds = getSelectedRows();
            if (dataIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "test/stress/delete",
                    contentType: "application/json",
                    data: JSON.stringify(dataIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.showEdit = false;
            vm.showOrderModes=false;
            vm.showOrderEdit=false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'dataName': vm.q.dataName},
                page: page
            }).trigger("reloadGrid");
        },
        validator: function () {
            if (isBlank(vm.dataInstance.dataName)) {
                alert("用例名称不能为空");
                return true;
            }


            if (isBlank(vm.dataInstance.operator)) {
                alert("操作人不能为空");
                return true;
            }
        }

        // uploadFiles: function () {
        //     var caseId = getSelectedRow();
        //     if (caseId == null) {
        //         return;
        //     }
        //
        //     $('#files').fileinput('upload');
        //
        // }
    }
});


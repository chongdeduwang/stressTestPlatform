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
            {label: '备注', name: 'remark', sortable: false, width: 110},
            {
                label: '执行操作', name: '', width: 95, sortable: false, formatter: function (value, options, row) {
                    var btn = '';
                    if (!(getExtension(row.originName) && /^(jmx)$/.test(getExtension(row.originName).toLowerCase()))) {
                        btn = "<a href='#' class='btn btn-primary' onclick='synchronizeFile(" + row.fileId + ")' ><i class='fa fa-arrow-circle-right'></i>&nbsp;同步文件</a>";
                    } else {
                        if (row.status == 1) {
                            btn = "<a href='#' class='btn btn-danger' onclick='stopOnce(" + row.fileId + ")' ><i class='fa fa-stop-circle'></i>&nbsp;停止</a>";
                        } else {
                            btn = "<a href='#' class='btn btn-primary' onclick='runOnce(" + row.fileId + ")' ><i class='fa fa-arrow-circle-right'></i>&nbsp;启动</a>";
                        }
                    }
                    // var stopBtn = "<a href='#' class='btn btn-primary' onclick='stop(" + row.fileId + ")' ><i class='fa fa-stop'></i>&nbsp;停止</a>";
                    // var stopNowBtn = "<a href='#' class='btn btn-primary' onclick='stopNow(" + row.fileId + ")' ><i class='fa fa-times-circle'></i>&nbsp;强制停止</a>";
                    var downloadFileBtn = "&nbsp;&nbsp;<a href='" + baseURL + "test/stressFile/downloadFile/" + row.fileId + "' class='btn btn-primary'><i class='fa fa-download'></i>&nbsp;下载</a>";
                    return btn + downloadFileBtn;
                }
            }
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
        showOrderEdit: false,
        orderContent: {},
        title: null,
        dataInstance: {},
        content: {},
        modes: 'pick',
        orderMode: 0,
        inputs: [],
        radio: 3
    },
    methods: {
        changeModes: function () {
            vm.inputs = [],
                $.ajax({
                    type: "GET",
                    url: baseURL + "/template/manage/" + vm.modes + "/" + vm.orderMode,
                    success: function (r) {
                        if (r.code === 0) {
                            vm.showOrderEdit = true;
                            vm.orderContent = r.template.content;
                            const order = JSON.parse(vm.orderContent);
                            console.log(order);
                            for (var key in order) {
                                if (!(order[key] instanceof Object)) {
                                    if (order[key].search("input") !== -1) {
                                        const pattern = new RegExp(".*?(?=\\()");
                                        const placeHolder = order[key].match(pattern)[0];
                                        vm.inputs.push({
                                            key,
                                            type: "input",
                                            placeHolder: placeHolder,
                                            label: placeHolder
                                        });
                                    } else if (order[key].search("checkbox") !== -1) {
                                        const pattern = new RegExp(".*?(?=\\()");
                                        const label = order[key].match(pattern)[0];
                                        vm.inputs.push({
                                            key,
                                            type: "checkbox",
                                            checked: key,
                                            label: label
                                        });
                                    }
                                } else {

                                }
                            }

                            console.log(vm.inputs)
                        } else {
                            alert(r.msg);
                        }
                    }
                });
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
            vm.dataInstance["content"] = JSON.stringify(vm.content);
            const tem = vm.modes + vm.orderMode;
            switch (tem) {
                case "pick0":
                    vm.dataInstance["templateId"] = "1";
                    break;
                case "pick1":
                    vm.dataInstance["templateId"]= "2";
                    break;
                case "rep0":
                    vm.dataInstance["templateId"] = "3";
                    break;
                case "rep1":
                    vm.dataInstance["templateId"] = "4";
                    break;
            }
            console.log(JSON.stringify(vm.dataInstance) + "formJson");

            if (vm.validator()) {
                return;
            }


            var url = vm.dataInstance.id == null ? "/order/manage/save" : "/order/manage/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.dataInstance),
                success: function (r) {
                    if (r.code === 0) {
                        // alert('操作成功', function(){
                        vm.reload();
                        // });
                    } else {
                        alert(r.msg);
                    }
                }
            });
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
            vm.showOrderEdit = false;
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
            if (isBlank(vm.dataInstance.templateId)) {
                alert("生成方式不能为空");
                return true;
            }
            if (isBlank(vm.dataInstance.content)) {
                alert("生成配置不能为空");
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


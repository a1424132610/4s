
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>4S维修管理系统</title>
    <!--核心JS-->
    <script type="text/javascript" src="/static/js/vue.min.js"></script>
    <!--elementui核心JS-->
    <link rel="stylesheet" href="/static/js/element-ui/lib/theme-chalk/index.css"/>
    <script type="text/javascript" src="/static/js/element-ui/lib/index.js"></script>
    <script type="text/javascript" src="/static/js/vue-resource.min.js"></script>
</head>



<body>
        <div id="app">
            <%--模态框--%>
                <el-dialog title="配件入库" :visible.sync="dialogFormVisible" width="400px" >
                    <el-form :model="form" class="demo-ruleForm" ref="filterForm" method="post">
                        <el-form-item label="配件名称" prop="partsName"
                                      :label-width="formLabelWidth"
                                      :rules="[
                                            { required: true, message: '名称不能为空'},
                                          ]"
                        >
                            <el-input type="partsName" v-model="form.partsName" autocomplete="off" clearable="true" style="width: auto"></el-input>
                        </el-form-item>
                        <el-form-item label="配件价格" prop="price"
                                      :label-width="formLabelWidth"
                                      :rules="[
                                            { required: true, message: '价格不能为空'},
                                            { type: 'number', message: '价格必须为数字值'}
                                          ]"

                        >
                         <el-input type="price" v-model.number="form.price" autocomplete="off"  clearable="true" style="width: auto"></el-input>
                        </el-form-item>
                        <el-form-item label="配件数量" prop="num"
                                      :label-width="formLabelWidth"
                                      :rules="[
                                            { required: true, message: '数量不能为空'},
                                            { type: 'number', message: '数量必须为数字值'}
                                          ]"
                        >
                         <el-input type="num" v-model.number="form.num" autocomplete="off"  clearable="true" style="width: auto"></el-input>
                        </el-form-item>
                        <el-form-item label="警告数量"  prop="warnNum"
                                      :label-width="formLabelWidth"
                                      :rules="[
                                            { required: true, message: '数量不能为空'},
                                            { type: 'number', message: '数量必须为数字值'}
                                          ]"
                        >
                         <el-input type="warnNum" v-model.number="form.warnNum" autocomplete="off"  clearable="true" style="width: auto"></el-input>
                        </el-form-item>
                        <el-form-item label="配件描述" :label-width="formLabelWidth" prop="context">
                         <el-input type="textarea" v-model="form.context" autocomplete="off"  clearable="true" style="width: auto"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="resetForm('filterForm')">重 置</el-button>
                        <el-button type="primary" @click="save('filterForm')">确 定</el-button>
                    </div>
                </el-dialog>

            <%--增删改查按钮--%>
            <div align="right">

                <el-button type="text" style="float: left;color: #333333;" disabled="true">配件管理</el-button>
                <el-autocomplete
                        style="width:25%"
                        class="inline-input"
                        v-model="inputName"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入配件名">
                </el-autocomplete>
                <el-button hit="false" color="white" @click="query(inputName)">搜索</el-button>
                <el-button hit="false" color="white" @click="add">添加</el-button>
                <el-button hit="false" color="white" @click="update">编辑</el-button>
                <el-button hit="false" color="white" @click="del">删除</el-button>
                <el-button hit="false" color="white" @click="load">刷新</el-button>
                <%--<el-button @click="setCurrent()">取消选中</el-button>--%>
            </div>
            <el-table
                    highlight-current-row
                    :data="tableData"
                    style="width: 100%"
                    border="true"
                    @row-click="openDetails"
                    <%--@current-change="handleCurrentChange"--%>
                    ref="singleTable"
                    :default-sort = "{prop:'id'}"

            >
                <el-table-column
                        prop="id"
                        label="编号"
                        width="180"
                        sortable>
                </el-table-column>
                <el-table-column
                        prop="partsName"
                        label="姓名"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="price"
                        label="价格"
                        sortable>
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="创建时间"
                        sortable>
                </el-table-column>
                <el-table-column
                        prop="num"
                        label="数量"
                        style="color: red"
                        <%--:style="this.num<=this.warnNum?'color: red':''"--%>
                        <%--font-color="red"--%>
                        sortable>
                </el-table-column>
                <el-table-column
                        prop="warnNum"
                        label="警告数量"
                        sortable>
                </el-table-column>
                <el-table-column
                        prop="context"
                        label="配件描述">
                </el-table-column>
            </el-table>
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[10, 20, 30, 40]"
                        :page-size="pagesize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total">
                </el-pagination>
        </div>
<%--<div>--%>
    <%--<img src="/static/images/广告位.jpg" style="height: 95px;width: 1550px;">--%>
<%--</div>--%>
</body>
<script type="text/javascript">
    var v = new Vue({
        el:"#app",
        data:{
            currentRow: null,
            inputName:"",
            oldData:[],
            total:"",
            currentPage:1, //初始页
            pagesize:10,    //    每页的数据
            dialogFormVisible:false,
            tableData: "",
            form:{
                partsName:"",
                price:"",
                num:"",
                warnNum:"",
                context:""
            },
            formLabelWidth: '100px'
        },
        created() {
            this.handlePartsList()
            this.getAll();
        },
         methods: {
            // 初始页currentPage、初始每页数据数pagesize和数据data
             handleSizeChange: function (pagesize) {
                 //每页下拉显示数据
                this.pagesize = pagesize;
                 this.handlePartsList();
            },
            handleCurrentChange: function(currentPage){
                //点击第几页
                this.currentPage = currentPage;
                this.handlePartsList();
            },
            handlePartsList() {
                this.$http.get('/parts/selectAll',{params:{page:this.currentPage,rows:this.pagesize}}).then(res => {
                    this.tableData = res.body.rows
                    this.total = res.body.total
                })
            },
             //查询单条数据
             query:function (inputName) {
                 //传给后台输入框的数据查询返回结果
                 this.$http.post("/parts/getOne",{inputName},{emulateJSON:true}).then(function (value) {
                     this.tableData = new Array(value.body)
                     this.total = 1;
                 })
             },
             //获取所有数据
            getAll: function () {
                this.$http.get("/parts/getAll").then(function (value) {
                    this.oldData = value.body;
                })
            },
            add:function () {

                //清空数据
                 this.resetForm('filterForm');
                //开启弹框
                this.dialogFormVisible=true;
                this.form={}
            },
            openDetails(event){
                this.id= event.id;
                this.partsName= event.partsName;
                this.price= event.price;
                this.num= event.num;
                this.warnNum= event.warnNum;
                this.context= event.context;
            },
            update:function () {
                //回显数据
                if(this.id==null){
                    this.$alert('选中再修改', 'QAQ', {
                        confirmButtonText: '确定'});
                    return
                }
                this.form.partsName= this.partsName;
                this.form.price= this.price
                this.form.num= this.num
                this.form.warnNum= this.warnNum
                this.form.context= this.context
                this.form.id= this.id

                //开启弹框
                this.dialogFormVisible=true;

            },
             //删除
            del:function () {
                if(this.id==null){
                    this.$alert('选中再删除', 'QAQ', {
                        confirmButtonText: '确定'});
                    return
                }
                //提交数据给后台
                this.$http.post("/parts/delete",{id:this.id},{emulateJSON:true}).then(function (res) {
                    //后台返回结果
                    if (res.body.success){
                        this.$alert('删除成功', '成功', {
                            confirmButtonText: '确定',
                            <%--callback: action => {--%>
                            <%--this.$message({--%>
                            <%--type: 'info',--%>
                            <%--message: `action: ${ action }`--%>
                            <%--});--%>
                            <%--}--%>
                        });
                        this.handlePartsList();
                        this.setCurrent();
                    }else{
                       alert("失败");
                    }
                })
            },
             //刷新方法返回第一页
            load:function () {
                // this.handlePartsList();
                this.$http.get('/parts/selectAll',{params:{page:this.currentPage=1,rows:this.pagesize}}).then(res => {
                    this.tableData = res.body.rows;
                    this.total = res.body.total;
                    this.setCurrent();
                })
            },
             //保存from表单提交数据给后台
            save:function(formName) {
                var formData = new FormData();
                formData.append('partsName', this.form.partsName);
                formData.append('price', this.form.price);
                formData.append('num', this.form.num);
                formData.append('warnNum', this.form.warnNum);
                formData.append('context', this.form.context);
                if (this.form.id!=null){
                    formData.append('id', this.form.id);
                }

                this.$refs[formName].validate(function (valid) {
                    //进行表单验证
                    if (!valid) {
                        this.$alert('自己干了什么心里没数吗', '错误', {
                            confirmButtonText: '确定',
                            <%--callback: action => {--%>
                            <%--this.$message({--%>
                            <%--type: 'info',--%>
                            <%--message: `action: ${ action }`--%>
                            <%--});--%>
                            <%--}--%>
                        });
                        return false
                    }
                });
                this.$http.post("/parts/saveOrUpdate",formData,{emulateJSON:true}).then(function (res) {
                    if (res.body.success){
                        //弹窗
                        this.$alert('操作成功！请再刷新一下', '成功', {
                            confirmButtonText: '确定',
                            <%--callback: action => {--%>
                                <%--this.$message({--%>
                                    <%--type: 'info',--%>
                                    <%--message: `action: ${ action }`--%>
                                <%--});--%>
                            <%--}--%>
                        });
                        this.resetForm('filterForm');
                        //关闭模态框
                        this.dialogFormVisible=false;
                        //刷新界面
                        this.handlePartsList();

                    }
                });
            },
            resetForm:function(formName){

                this.$nextTick(function (){
                    return this.$refs[formName].resetFields();
            })
            },
             //取消选中
             setCurrent(row) {
                 this.$refs.singleTable.setCurrentRow(row);
                 this.id=null;
             },
             // handleCurrentChange(val) {
             //     this.currentRow = val;
             // },
             querySearch(queryString, cb) {
                 var csv = this.csvS;
                 cb(csv);
             }
        },
        watch:{
            inputName:{
                handler: function () {
                    this.csvS = [];//这是定义好的用于存放下拉提醒框中数据的数组
                    var len = this.oldData.length;//oldData是页面初始化时非分页查询得到的初始全部数据
                    var arr = [];
                    this.$nextTick(()=>{
                        for (var i = 0; i < len; i++) {
                            //根据输入框中inputName的值进行模糊匹配
                            if (this.oldData[i].partsName.indexOf(this.inputName) >= 0) {
                                arr.push(this.oldData[i].partsName);//符合条件的值都放入arr中
                            }
                        }
                        //el - autocomplete元素要求数组内是对象，且有value属性，此处做一个格式调整
                        for (var i = 0; i < arr.length; i++) {
                            var obj = {value: ""};
                            obj.value = arr[i];
                            this.csvS.push(obj);
                        }
                    })
                }
            }
        }
    });
</script>
</html>


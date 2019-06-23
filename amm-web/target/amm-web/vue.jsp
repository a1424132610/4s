
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/static/js/element-ui/lib/theme-chalk/index.css"/>
    <script src="/static/js/vue.min.js"></script>
    <script src="/static/js/element-ui/lib/index.js"></script>
    <script src="/static/js/vue-resource.min.js"></script>
    <script src="/static/js/vue-router.js"></script>
</head>

<style>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
    html,body,.el-container,#app{
        width: 100%;
        height: 100%;
    }
    .el-row {
        margin-bottom: 20px
    &:last-child {
         margin-bottom: 0;
     }
    }
    .el-col {
        border-radius: 4px;
    }
    .bg-purple-dark {
        background: #99a9bf;
    }
    .bg-purple {
        background: #d3dce6;
    }
    .bg-purple-light {
        background: #e5e9f2;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>
<body>
    <div id="app">
        <el-container>
            <el-aside width="200px">
                <el-row>
                    <el-col>
                        <el-menu
                                class="el-menu-vertical-demo"
                                @open="handleOpen"
                                router=true
                                @close="handleClose"
                                background-color="#545c64"
                                text-color="#fff"
                                active-text-color="#ffd04b">
                            <el-submenu  v-for="m in menu" :index="m.id">
                                <template slot="title" v-if="m">
                                    <i class="el-icon-location"></i>
                                    <span>{{m.name}}</span>
                                </template>
                                <el-menu-item-group v-for="sub in m.children">
                                    <el-menu-item :index="sub.url">{{sub.name}}</el-menu-item>
                                </el-menu-item-group>
                            </el-submenu>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>
            <el-container>
                <el-header>

                </el-header>
                <%--中部显示数据表格--%>
                <el-main>
                    <div style="display:flex;height: 43px;">
                        <el-input
                                type="text"
                                placeholder="请输入名字"
                                style="width:30%"
                                clearable>
                        </el-input>
                        <el-button type="success"  style="margin-left: 10px;height: 40px" plain>搜索</el-button>
                        <el-button type="primary" style="margin-right: 30px;height: 40px" @click="dialogFormVisible = true;upd()">添加</el-button>
                    </div>
                    <%--表格展示数据--%>
                    <compnent_table ref="com_table" @load="reflash" @upd="upd"></compnent_table>
                    <el-dialog width="40%"  title="添加/修改" :visible.sync="dialogFormVisible" :before-close="close">
                        <%--form表单组件--%>
                        <component_form ref="children_form" @load="reflash"></component_form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogFormVisible = false;close()">取 消</el-button>
                            <el-button type="primary" @click="dialogFormVisible = false;add()">确 定</el-button>
                        </div>
                    </el-dialog>
                </el-main>
            </el-container>
        </el-container>
    </div>
<%--数据展示以及分页--%>
<template id="tableGrid">
    <div class="block">
        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="角色名称">
                            <span>{{ props.row.name }}</span>
                        </el-form-item>
                        <el-form-item label="编号">
                            <span>{{ props.row.sn }}</span>
                        </el-form-item>
                        <el-form-item label="权限">
                            <span  v-for="per in props.row.permissions">
                                {{ per.name }}
                            </span>
                        </el-form-item>
                        <el-form-item label="资源">
                            <span  v-for="res in props.row.permissions">
                                {{ res.resource.url}}&nbsp&nbsp
                            </span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
                    label="编号"
                    prop="sn">
            </el-table-column>
            <el-table-column
                    label="角色名称"
                    prop="name">
            </el-table-column>
            <el-table-column
                    label="操作"
                    align="center">
                <template slot-scope="scope">
                    <el-button
                            type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"  circle></el-button>
                    <el-button type="danger" icon="el-icon-delete" @click="del(scope.$index,scope.row)" circle></el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="1"
                :page-sizes="[5, 10, 20, 30]"
                :page-size=5
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                style="text-align: right"
                :hide-on-single-page="true"
                background
            >
        </el-pagination>
    </div>
</template>
<%--form表单--%>
<template id="formTable">
    <el-form :model="form" style="height: 100px;">
        <el-form-item align="left" label="编号:" label-position="left" label-width="90px">
            <el-input v-model="form.sn" style="width:260px;" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item  align="left" label="角色名字:" label-position="left" label-width="90px">
            <el-input v-model="form.name" style="width:260px;" autocomplete="off"></el-input>
        </el-form-item>
        <%--<el-form-item  align="left" label="角色名字:" label-position="left" label-width="90px">
        </el-form-item>--%>
        <div>
            <el-checkbox v-for="(item,index) in this.Allpermissions" @change="ischange(item)"  :checked="item.isActive" border>{{item.name}}</el-checkbox>
        </div>

    </el-form>
</template>

</body>
<script>
    //定义数据表格组件
    var compnent_table = {
        template:"#tableGrid",
        data:function () {
            return {
                tableData:[],
                total:"",
                pageSize:5
            }
        },
        created(){
            this.getAll();
        },
        methods:{
            //改变页面数量
            handleSizeChange(val) {
                this.pageSize=val;
                this.$http.get(
                    "/role/page",
                    {params:{rows:val}}
                ).then(res => {
                    this.setData(res);
                });
            },
            //翻页
            handleCurrentChange(val) {
                this.$http.get(
                    "/role/page",
                    {params:{page:val,rows:this.pageSize}}
                ).then(res => {
                    this.setData(res);
                });
            },
            setData(res){
                this.total = res.body.total;
                this.tableData = res.body.rows;
            },
            del(index,row){
                //发送请求
                this.$http.get("/role/delete?id="+row.optid).then(res=>{
                    if(res.body.success == true){
                        this.$message({
                            showClose:true,
                            message:res.body.msg,
                            type:'success'
                        });
                        this.$emit("load");
                    }else {
                        this.$message({
                            showClose:true,
                            message:res.body.msg,
                            type:'warning'
                        });
                    }
                });
            },
            getAll(){
                this.$http.get(
                    "/role/page?rows="+this.pageSize
                ).then(res => {
                    this.setData(res);
                });
            },
            edit(index,row){
                this.$emit('upd',row);
            }
        }
    }
    //定义form表单
    var component_form = {
        template: "#formTable",
        data: function () {
            return {
                form: {
                    name: '',
                    sn: '',
                    permissions: []
                },
                Allpermissions: []
            }
        },
        methods: {
            add() {
                //将form表单的值存进新的对象中
                var data = {};
                for (var v in this.form) {
                    data[v] = this.form[v];
                }
                //发送请求添加
                 this.$http.post("/role/saveOrUpdate",data).then(res=>{
                     if(res.body.success == true){
                         this.$message({
                             showClose:true,
                             message:res.body.msg,
                             type:'success'
                         });
                         this.$emit("load");
                     }else{
                         this.$message({
                             showClose: true,
                             message: res.body.msg,
                             type: 'warning'
                         });
                     }
                 });
            },
            upd(row) {
                this.$http.get("/permission/page").then(res => {
                    this.Allpermissions = res.body.rows;
                    if(row){
                        this.form = row;
                        for(var i in this.Allpermissions){
                            for (var v in row.permissions){
                                if (this.Allpermissions[i]["id"] == row.permissions[v].id){
                                    this.Allpermissions[i]["isActive"] = true;
                                }
                            }
                        }
                    }
                });

            },
            ischange(item){
                for(var i in this.form.permissions){
                    if(this.form.permissions[i].id === item.id){
                        this.form.permissions.splice(i,1);
                        return;
                    }
                }
                this.form.permissions.push(item);
            }
        }
    }

    new Vue({
        el:"#app",
        data:{
            menu:[],
            dialogFormVisible: false
        },
        components:{
            compnent_table:compnent_table,
            component_form:component_form
        },
        created(){
            //自动调用
            this.getMenu();
        },
        methods:{
            handleOpen(key, keyPath) {

            },
            handleClose(key, keyPath) {

            },
            getMenu(){
                //请求菜单
                this.$http.get("/menu/findMenus").then(res => {
                    this.menu = res.body;
                });
            },
            add(){
                //调用子组件的添加方法
                this.$refs.children_form.add();
            },
            reflash(){
                //重新刷新页面
                this.$refs.com_table.getAll();
            },
            upd(row){
                //等子组件渲染完毕后再调用子组件
                this.dialogFormVisible = true;
                this.$nextTick(()=>{
                    this.$refs.children_form.upd(row);
                })
            },
            close(){
                this.dialogFormVisible = false;
                this.$nextTick(()=>{
                    // this.$refs.children_form.resetField();
                });
            }
        }
    });

</script>



</html>

<!-- /* <template>
     <div>
		 <div>
			 <el-input ref="xsmc" v-model="key" size="mini" style="width: 200px; margin-left: 10px;"
			 	placeholder="请输入关键字..." prefix-icon="fa fa-search" @keydown.enter.native="search">
			 </el-input>
			 <el-button  plain type="primary" size="mini" style="margin-left: 10px;" icon="fa fa-search" @click="search"></el-button>
			  <el-button plain type="success" size="mini" style="margin-left: 10px;" icon="fa fa-calendar-plus-o" @click="showAdd"> 添加新菜单</el-button>
		 </div>
		 <div style="margin-top: 15px;">
		 	<el-table :data="tableData" size="mini" border stripe style="width: 90%" @selection-change="handleSelectionChange">
		 		<el-table-column type="selection" width="55" />
		 		<el-table-column prop="id" label="编号" width="80" />
		 		<el-table-column prop="mingCheng" label="菜单名称" width="100" />
		 		<el-table-column prop="url" label="访问地址" width="150" />
				<el-table-column prop="path" label="权限" width="150" />
				<el-table-column prop="fuCaiDanId" label="父菜单" width="80" />
				<el-table-column prop="icon" label="图标名称" width="200" />
				<el-table-column prop="paiXu" label="排序" width="55" />
				<el-table-column label="操作">
		 			<template #default="scope">
		 				<el-button plain size="mini" icon="fa fa-pencil"  @click="handleEdit(scope.$index, scope.row)"></el-button>
		 				<el-button plain size="mini" type="danger" icon="fa fa-trash"  @click="handleDelete(scope.$index, scope.row)">
		 				</el-button>
		 			</template>
		 		</el-table-column>
		 	</el-table>
		 	<el-button type="danger" size="mini" style="margin-top: 10px;"
		 		:disabled="this.multipleSelection.length == 0" @click="deleteDatas()">批量删除</el-button>
		 </div>
		 <div>
			 <el-dialog v-model="dialogShow" width="30%" :title="title">
			 	<div style="margin-bottom: 10px;">
			 		<el-tag type="success">菜单名称</el-tag>
			 		<el-input v-model="item.mingCheng" size="mini" style="width: 100%;margin-top: 10px;"
			 			prefix-icon="el-icon-edit">
			 		</el-input>
			 	</div>
			 	<div style="margin-bottom: 10px;">
			 		<el-tag type="success">访问地址</el-tag>
			 		<el-input v-model="item.url" size="mini" style="width: 100%; margin-top: 10px;"
			 			prefix-icon="el-icon-edit" placeholder="必须以'/'开头，如:/xxxx" > 
			 		</el-input>
			 	</div>
			    <div style="margin-bottom: 10px;">
			    	<el-tag type="success">访问权限</el-tag>
			    	<el-input v-model="item.path" size="mini" style="width: 100%; margin-top: 10px;"
			    		prefix-icon="el-icon-edit" placeholder="必须以'/'开头，如:/xxxx" > 
			    	</el-input>
			    </div>
				<div style="margin-bottom: 10px;">
					<el-tag type="success">上级菜单</el-tag>
					<el-select v-model="item.fuCaiDanId" placeholder="Select">
					    <el-option
					      v-for="option in yiJi"
					      :key="option.id"
					      :label="option.mingCheng"
					      :value="option.id"
					    >
					    </el-option>
					  </el-select>
				</div>
				<div style="margin-bottom: 10px;">
					<el-tag type="success">菜单排序</el-tag>
					<el-input v-model="item.paiXu" size="mini" style="width: 100%; margin-top: 10px;"
						prefix-icon="el-icon-edit" > 
					</el-input>
				</div>
				<div style="margin-bottom: 10px;">
					<el-tag type="success">图标名称</el-tag>
					<el-input v-model="item.icon" size="mini" style="width: 100%; margin-top: 10px;"
						prefix-icon="el-icon-edit" > 
					</el-input>
				</div>
			 	<template #footer>
			 		<span class="dialog-footer">
			 			<el-button @click="cancelDialog()">取消</el-button>
			 			<el-button type="primary" @click="submitDialog()">确定</el-button>
			 		</span>
			 	</template>
			 </el-dialog>
		 </div>
	 </div>
</template>

<script>
	export default {
		data() {
			return {	
				yiJi:[],
				tableData: [],
				multipleSelection: [],
				key: '',
				title: '',
				dialogShow: false,
				item:{
					        "id": null,
					        "mingCheng": null,
					        "url": null,
					        "path": null,
					        "fuCaiDanId": 1,
					        "paiXu": 1,
					        "icon": 'fa fa-link'
				},
				index: 0
			}
		},
		mounted() {
			this.initCaiDanList()
			this.getYiji()
		},
		methods: {
			getYiji(){
				this.$api.getRequest("/api/system/config/yiji/").then(
					resp => {
						this.yiJi = resp.responseData
					}
				)
			},
            search(){
				this.$api.getRequest("/api/system/config/caidanlist/"+this.key).then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			},
			showAdd(){
				this.title="添加新菜单"
				this.dialogShow =true
			},
			cancelDialog(){
				this.$message({
					type: 'info',
					message: '用户取消删除操作',
				})
				this.dialogShow = false
				this.item = {
				    "id": null,
				    "mingCheng": null,
				    "url": null,
				    "path": null,
				    "fuCaiDanId": 1,
				    "paiXu": 1,
				    "icon": 'fa fa-link'
				   }
			},
			add(){
				if (!this.item.mingCheng || this.item.mingCheng == '') {
					this.$message.error("请输入菜单名称！")
					return;
				}
				this.$api.postRequest("/api/system/config/caidan", this.item).then(
					resp => {
				        this.item = {
					        "id": null,
					        "mingCheng": null,
					        "url": null,
					        "path": null,
					        "fuCaiDanId": 1,
					        "paiXu": 1,
					        "icon": 'fa fa-link'
				           }
						this.initCaiDanList()
						this.dialogShow = false
					}
				)
			},
			edit(){
				let that = this
				if (!this.item.mingCheng || this.item.mingCheng == '') {
					this.$message.error("请输入菜单名称！")
					return;
				}
				this.$api.putRequest("/api/system/config/caidan", this.item).then(
					resp => {
						that.tableData[that.index] = that.item
				        that.item = {
					        "id": null,
					        "mingCheng": null,
					        "url": null,
					        "path": null,
					        "fuCaiDanId": 1,
					        "paiXu": 1,
					        "icon": 'fa fa-link'
				           }
						// this.initCaiDanList()
						
						that.dialogShow = false
					}
				)				
			},
			submitDialog(){
				if(this.item.id == null){
					this.add()
				}else{
					this.edit()
				}
			},
			initCaiDanList() {
				// this.tableData = []
				this.$api.getRequest("/api/system/config/caidanlist").then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			},
			
			handleSelectionChange(val) {
				this.multipleSelection = val
			},	
	
			//点击编辑按钮
			handleEdit(index, data) { //拿原来的旧值
                this.title="编辑菜单"
				this.item = data
				this.index = index
				this.dialogShow = true
			},
	
			//点击删除按钮
			handleDelete(index, data) {
	             let that = this
	             this.$confirm('您真的要删除编号为:[' + data.id + ']的数据吗？',
	             		'请再次确认操作', {
	             			confirmButtonText: '确认',
	             			cancelButtonText: '取消',
	             			type: 'warning'
	             		},
	             	)
	             	.then(() => {
	             		that.$api.deleteRequest("/api/system/config/caidan/" + data.id).then(() => {
	             			that.initCaiDanList()
	             		})
	             	})
	             	.catch(() => {
	             		this.$message({
	             			type: 'info',
	             			message: '用户取消删除操作',
	             		})
	             	})
			},
			deleteDatas(){
				this.$confirm('您真的要删除吗？','请再次确认操作',{
					confirmButtonText: '确认',
					cancelButtonText: '取消',
					type: 'warning'
				})
				.then(()=>{
				     let ids = []
				     for (let i = 0; i < this.multipleSelection.length; i++) {
					      ids.push(this.multipleSelection[i].id)      //把勾选项的id放进ids数组中
				        }
				    this.$api.deleteRequest("/api/system/config/caidan",ids).then((res)=>{
					
					//方法一：删除成功后之间刷新表单
					// this.initJueSeList() 
					
					//方法二：删除成功后将勾选的项从tableData中移除
					for(let j = 0; j< ids.length; j++){
						for(let k = 0; k< this.tableData.length; k++){
							if(this.tableData[k].id == ids[j]){
								this.tableData.splice(k,1)
								break
							}
						}
					}
				})	
			})
				
				

			}
		}
	}
</script>

<style>
</style> */ -->

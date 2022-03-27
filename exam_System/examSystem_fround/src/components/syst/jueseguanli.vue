<template>
	<div>
		<div >
			<el-input v-model="jueSe.jueSeMingCheng" size="mini" style="width: 200px;" placeholder="添加角色名称..."
				prefix-icon="fa fa-user-plus" @keydown.enter.native="jumpXianShiMingCheng">
			</el-input>
			<el-input ref="xsmc" v-model="jueSe.xianShiMingCheng" size="mini" style="width: 200px;margin-left: 10px;"
				placeholder="添加角色显示名称..." prefix-icon="fa fa-user-plus" @keydown.enter.native="addJusSe">
			</el-input>
			<el-button plain type="primary" size="mini" style="margin-left: 10px;" icon="fa fa-plus"
				@click="addJueSe()"></el-button>
		</div>
		<!-- 表格部分 -->
		<div >
			<el-table :data="tableData" size="small" border stripe style="width: 60% "  @selection-change="handleSelectionChange">
				<el-table-column type="selection" width="55" />
				<el-table-column prop="id" label="编号" width="80" />
				<el-table-column prop="jueSeMingCheng" label="角色名称" width="180" />
				<el-table-column prop="xianShiMingCheng" label="显示名称" width="240" />
				<el-table-column label="操作" width="155">
					<template #default="scope">
						<el-button plain size="mini" @click="handleEdit(scope.$index, scope.row)" icon="fa fa-pencil">
						</el-button>
						<el-button size="mini" plain type="danger" @click="handleDelete(scope.$index, scope.row)"
							icon="fa fa-trash">
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-button  type="danger" size="mini" style="margin-top: 10px;" :disabled="this.multipleSelection.length == 0" @click="deleteDatas()">批量删除</el-button>
		</div>
		<!-- 编辑内容部分-->
		<div>
			<el-dialog v-model="editShow" width="30%" :title="editTitle">
				<div style="margin-bottom:10px ;">
					<el-tag type="info" style="margin-right: 10px;">角色名称</el-tag>
					<el-input v-model="edit.jueSeMingCheng" size="mini" style="width: 200px;" placeholder="添加角色名称..."
						prefix-icon="fa fa-user-plus">
					</el-input>
				</div>
				<div>
					<el-tag type="info" style="margin-right: 10px;">显示名称</el-tag>
					<el-input v-model="edit.xianShiMingCheng" size="mini"
						style="width: 200px;margin-right: 10px;" placeholder="添加角色显示名称..." prefix-icon="fa fa-user-plus">
					</el-input>
				</div>

				<template #footer>
					<span class="dialog-footer">
						<el-button @click="cancelJuese()" size="mini">取消</el-button>
						<el-button type="primary" @click="editJueSe()" size="mini">确认</el-button>
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
				jueSe: {
					jueSeMingCheng: '',
					xianShiMingCheng: ''
				},
				tableData: [],
				editShow: false,
				edit:{
					jueSeMingCheng: '',
					xianShiMingCheng: '',
					id:null
				},
				editTitle:'编辑内容',
				
				multipleSelection:[]
			}
		},
		mounted() {
			this.initJueSeList()
		},
		methods: {
			addJueSe() {
				if (this.jueSe.jueSeMingCheng == '') {
					this.$message.error("请输入角色名称！");
					return;
				}
				this.$api.postRequest("/api/system/config/juese", this.jueSe).then(
					resp => {
						this.jueSe.jueSeMingCheng = ''
						this.jueSe.xianShiMingCheng = ''
						this.initJueSeList()
					}
				)
			},
			initJueSeList() {
				this.tableData=[]
				this.$api.getRequest("/api/system/config/juese").then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			},
			//点击编辑
			handleEdit(index, data) {
				let that=this
				this.$api.getRequest("/api/system/config/juese/"+data.id)
				.then((res)=>{
					that.edit=res.responseData
					that.editTitle='编辑编号为'+that.edit.id+'的内容'
				})
				this.editShow = true
			},
			jumpXianShiMingCheng2() {
				this.$refs.xsmc.focus()
			},
			editJueSe() {
				let that=this
				this.$api.putRequest("/api/system/config/juese",this.edit)
				.then((res)=>{
					that.initJueSeList()
				})
				this.editShow=false
			},
			cancelJuese(){
				this.$message({
					type: 'info',
					message: '已取消删除操作',
				})
				this.editShow=false
			},
			
			//点击删除
			handleDelete(index, data) {
				let that = this
				this.$confirm('确认删除编号〖' + data.id + '〗的角色信息吗？', '提示', {
						confirmButtonText: "确定",
						cancelButtonText: "取消",
						type: 'warning'
					})
					.then(() => {
						that.$api.deleteRequest("/api/system/config/juese/" + data.id)
							.then((res) => {
								that.initJueSeList()
							})
					})
					.catch(() => {
						this.$message({
							type: 'info',
							message: '已取消删除操作',
						})
					})
			},
			handleSelectionChange(val) {
			      this.multipleSelection = val
			},
			deleteDatas() {
				// for(let i=0;i<this.multipleSelection.length;i++){
				// 	this.$api.deleteRequest("/api/system/config/juese/"+this.multipleSelection[i].id)
				// }
				// this.initJueSeList()
				let ids=[]
				for(let i=0 ; i<this.multipleSelection.length;i++){
					ids.push(this.multipleSelection[i].id)
				}
				this.$api.deleteRequest("/api/system/config/juese",ids).then((res)=>{
					for(let j=0;j<ids.length;j++){
						for(let k=0;k<this.tableData.length;k++){
							if(this.tableData[k].id==ids[j]){
								this.tableData.splice(k,1)
								break
							}
						}
					}
				})
			}
		}
	}
</script>

<style>
</style>

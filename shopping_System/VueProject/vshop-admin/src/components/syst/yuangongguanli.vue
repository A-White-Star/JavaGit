<template>
	<div>
		<div>
			<el-input ref="xsmc" v-model="key" size="mini" style="width: 200px; margin-left: 10px;"
				placeholder="请输入关键字..." prefix-icon="fa fa-search" @keydown.enter.native="search">
			</el-input>
			<el-button plain type="primary" size="mini" style="margin-left: 10px;" icon="fa fa-search" @click="search">
			</el-button>
			<el-button plain type="success" size="mini" style="margin-left: 10px;" icon="fa fa-user-plus"
				@click="showAdd">添加新员工</el-button>
		</div>
		<div style="margin-top: 15px;">
			<el-table :data="tableData" size="mini" border stripe style="width: 90%"
				@selection-change="handleSelectionChange">
				<el-table-column type="selection" width="55" />
				<el-table-column prop="id" label="编号" width="80" />
				<el-table-column prop="yongHuMing" label="用户名" width="100" />
				<el-table-column prop="zhenShiXingMing" label="真实姓名" width="150" />
				<el-table-column prop="xingBie" label="性别" width="150" />
				<el-table-column prop="shouJiHao" label="手机号" width="80" />
				<el-table-column label="操作">
					<template #default="scope">
						<el-button plain size="mini" icon="fa fa-pencil"
							@click="handlePassword(scope.$index, scope.row)">初始化密码</el-button>
						<el-button plain size="mini" icon="fa fa-pencil" @click="handleEdit(scope.$index, scope.row)">
						</el-button>
						<el-button plain size="mini" type="danger" icon="fa fa-trash"
							@click="handleDelete(scope.$index, scope.row)">
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
					<el-tag type="success">用户</el-tag>
					<el-input v-model="item.yongHuMing" size="mini" style="width: 100%;margin-top: 10px;"
						prefix-icon="el-icon-edit">
					</el-input>
				</div>
				<div style="margin-bottom: 10px;">
					<el-tag type="success">真实姓名</el-tag>
					<el-input v-model="item.zhenShiXingMing" size="mini" style="width: 100%; margin-top: 10px;"
						prefix-icon="el-icon-edit">
					</el-input>
				</div>
				<div style="margin-bottom: 10px;">
					<el-tag type="success">性别</el-tag>
					<el-select v-model="item.xingBie" placeholder="请选择性别" size="mini"
						style="width: 100%; margin-top: 10px;" prefix-icon="el-icon-edit">
						<el-option key="1" label="男" value="true"></el-option>
						<el-option key="0" label="女" value="false"></el-option>
					</el-select>
				</div>
				<div style="margin-bottom: 10px;">
					<el-tag type="success">手机号</el-tag>
					<el-input v-model="item.shouJiHao" size="mini" style="width: 100%; margin-top: 10px;"
						prefix-icon="el-icon-edit">
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
				tableData: [],
				multipleSelection: [],
				key: '',
				title: '',
				dialogShow: false,
				item: {
					"id": null,
					"yongHuMing": null,
					"zhenShiXingMing": null,
					"xingBie": null,
					"shouJiHao": null,
				},
				index: 0
			}
		},
		mounted() {
			this.initYuanGongList()
		},
		methods: {
			search() {
				this.tableData = []
				this.$api.getRequest("/api/system/config/yuangong/" + this.key)
					.then(
						resp => {
							for (let x in resp.responseData) {
								resp.responseData[x].xingBie = resp.responseData[x].xingBie ? '男' : '女'
								this.tableData.push(resp.responseData[x])
							}
						}
					)
			},
			showAdd() {
				this.title = "添加新员工"
				this.dialogShow = true
			},
			cancelDialog() {
				this.$message({
					type: 'info',
					message: '用户取消删除操作',
				})
				this.dialogShow = false
				this.item = {
					"id": null,
					"yongHuMing": null,
					"zhenShiXingMing": null,
					"xingBie": null,
					"shouJiHao": null,
				}
			},
			add() {
				if (!this.item.yongHuMing || this.item.yongHuMing == '') {
					this.$message.error("请输入用户名！")
					return;
				}
				if (!this.item.zhenShiXingMing || this.item.zhenShiXingMing == '') {
					this.$message.error("请输入真实姓名！")
					return;
				}
				if (!this.item.shouJiHao || this.item.shouJiHao == '') {
					this.$message.error("请输入手机号！")
					return;
				}

				this.$api.postRequest("/api/system/config/yuangong", this.item).then(
					resp => {
						this.item = {
							"id": null,
							"yongHuMing": null,
							"zhenShiXingMing": null,
							"xingBie": null,
							"shouJiHao": null,
						}
						this.initYuanGongList()
						this.dialogShow = false
					}
				)
			},
			edit() {
				let that = this
				if (!this.item.yongHuMing || this.item.yongHuMing == '') {
					this.$message.error("请输入用户名！")
					return;
				}
				if (!this.item.zhenShiXingMing || this.item.zhenShiXingMing == '') {
					this.$message.error("请输入真实姓名！")
					return;
				}
				if (!this.item.shouJiHao || this.item.shouJiHao == '') {
					this.$message.error("请输入手机号！")
					return;
				}
				//转换前台性别参数类型与后台性别参数类型一致
				if (this.item.xingBie == '男') this.item.xingBie = 1;
				if (this.item.xingBie == '女') this.item.xingBie = 0;
				//数据传回
				this.$api.putRequest("/api/system/config/yuangong", this.item).then(
					resp => {
						that.tableData[that.index] = that.item
						this.item = {
							"id": null,
							"yongHuMing": null,
							"zhenShiXingMing": null,
							"xingBie": null,
							"shouJiHao": null,
						}
						this.initYuanGongList()
						this.dialogShow = false
					}
				)
			},
			submitDialog() {
				if (this.item.id == null) {
					this.add()
				} else {
					this.edit()
				}
			},
			initYuanGongList() {
				this.tableData = []
				this.$api.getRequest("/api/system/config/yuangong")
					.then(
						resp => {
							for (let x in resp.responseData) {
								resp.responseData[x].xingBie = resp.responseData[x].xingBie ? '男' : '女'
								this.tableData.push(resp.responseData[x])
							}
						}

					)
			},

			handleSelectionChange(val) {
				this.multipleSelection = val
			},
			//点击编辑按钮
			handleEdit(index, data) { //拿原来的旧值
				this.title = "编辑"
				this.item = data
				this.index = index
				this.dialogShow = true
			},
			//点击编辑密码
			handlePassword(index, data) {
				this.$api.putRequest("/api/system/config/initpassword/" + data.id)
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
						that.$api.deleteRequest("/api/system/config/yuangong/" + data.id).then(() => {
							that.initYuanGongList()
						})
					})
					.catch(() => {
						this.$message({
							type: 'info',
							message: '用户取消删除操作',
						})
					})
			},
			deleteDatas() {
				this.$confirm('您真的要删除吗？', '请再次确认操作', {
						confirmButtonText: '确认',
						cancelButtonText: '取消',
						type: 'warning'
					})
					.then(() => {
						let ids = []
						for (let i = 0; i < this.multipleSelection.length; i++) {
							ids.push(this.multipleSelection[i].id) //把勾选项的id放进ids数组中
						}
						this.$api.deleteRequest("/api/system/config/yuangong", ids).then((res) => {

							//方法一：删除成功后之间刷新表单
							// this.initJueSeList() 

							//方法二：删除成功后将勾选的项从tableData中移除
							for (let j = 0; j < ids.length; j++) {
								for (let k = 0; k < this.tableData.length; k++) {
									if (this.tableData[k].id == ids[j]) {
										this.tableData.splice(k, 1)
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
</style>

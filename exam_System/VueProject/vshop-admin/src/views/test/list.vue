<template>
	<div>
		<div>
			<el-input ref="xsmc" v-model="key" size="mini" style="width: 200px; margin-left: 10px;"
				placeholder="请输入关键字..." prefix-icon="fa fa-search" @keydown.enter.native="search">
			</el-input>
			<el-button plain type="primary" size="mini" style="margin-left: 10px;" icon="fa fa-search" @click="search">
			</el-button>
		</div>
		<div style="margin-top: 15px;">
			<el-table :data="tableData" size="mini" border stripe style="width: 90%">
				<el-table-column prop="id" label="编号" width="80" />
				<el-table-column prop="testNumber" label="考试编号" width="100" />
				<el-table-column prop="testName" label="考试名称" width="150" />
				<el-table-column prop="date" label="考试时间" width="150" />
				<el-table-column prop="teacherName" label="监考教师" width="150" />
				<el-table-column prop="place" label="地点" width="150" />
				<el-table-column label="考试报名">
					<template #default="scope">
						<el-button plain size="mini" @click="handleEnroll(scope.$index, scope.row)">
							报名</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
		<div>
			<el-dialog v-model="FormShow" width="30%" :title="title">
				<el-form ref="form" :model="form" :rules="rules">
					<el-form-item label="考生名称" prop="username">
						<el-input type="text" v-model="item.username" autocomplete="off" placeholder="请输入考生姓名">
						</el-input>
					</el-form-item>
					<!-- <el-form-item label="班级" prop="class">
						<el-input type="text" v-model="item.class" autocomplete="off" placeholder="请输入考生姓名">
						</el-input>
					</el-form-item> -->
					<el-form-item label="性&nbsp;&nbsp;别" prop="sex">
						<el-select v-model="item.sex" size="small">
							<el-option v-for="item in options" :key="item.value" :label="item.label"
								:value="item.value" />
						</el-select>
					</el-form-item>
				</el-form>
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
				//form包含表单中的元素即成员对象。
				form: {
					username: '',
					sex: [],
				},
				//rules则是对应的form表单元素的一些显示规则等
				rules: {
					username: {
						required: true,
						message: '请输入名字',
						triggere: 'blur'
					},
					sex: {
						type: 'array',
						required: true,
						message: '请选择性别',
						triggere: 'blur'
					}

				},
				options: [{
						value: '1',
						label: '男',
					},
					{
						value: '0',
						label: '女',
					},
				],
				tableData: [],
				key: '',
				FormShow: false,
				item: {
					"id": null,
					"userNumber": '',
					"testName": '',
					"teacherName": '',
					"date":null,
					"place": '',
					"sex": null,
					"isDelete": false,
				},
			}
		},
		mounted() {
			this.initList()
		},
		methods: {
			search() {
				this.$api.getRequest("/api/houtai/testbykey/" + this.key).then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			},
			initList() {
				this.$api.getRequest("/api/houtai/test").then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			},
			handleEnroll(index, data) {
				let that = this
				this.$confirm('确认报名科目为:[' + data.testName + ']的考试吗？',
						'请再次确认操作', {
							confirmButtonText: '确认',
							cancelButtonText: '取消',
							type: 'warning'
						},
					)
					.then(() => {
						that.$api.deleteRequest("/api/houtai/test/" + data.id).then(() => {
							this.$message({
								type: 'sucess',
								message: '报名成功',
							})
							that.initList()
						})
					})
					.catch(() => {
						this.$message({
							type: 'info',
							message: '用户取消删除操作',
						})
					})

			},
			cancelDialog() {
				this.$message({
					type: 'info',
					message: '用户取消删除操作',
				})
				this.dialogShow = false
				this.item = {
					"id": null,
					"testNumber": '',
					"testName": '',
					"teacherName": '',
					"date":null,
					"place": '',
					"isDelete": false,
				}
			},
			submitDialog() {
				if (this.item.id == null) {
					this.add()
				} else {
					this.edit()
				}
			},
		}
	}
</script>

<style>
	.tr {
		height: 50px;
		padding: 50px;
		border-bottom: #A0AECB;

	}

	.imgStyle {
		width: 70%;
		margin: 0px auto;
		display: flex;
	}
</style>

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
				<el-table-column label="操作">
					<el-button plain size="mini" icon="fa fa-pencil" @click="handleEdit(scope.$index, scope.row)">
						录入成绩</el-button>
				</el-table-column>
			</el-table>
		</div>
		<div>
			<el-dialog v-model="dialogShow" width="30%" :title="title">
				<el-table :data="studentData" border style="width: 100%" @selection-change="handleSelectionChange">
					<el-table-column prop="student" label="学生姓名" width="180" />
					<el-table-column prop="score" label="成绩" width="200">
						<el-input ref="xsmc" size="mini" style="width: 200px; margin-left: 10px;" />
						<el-button size="mini" style="margin-top: 10px;" @click="submitDialog">提交</el-button>
					</el-table-column>
				</el-table>
				<el-button size="mini" style="margin-top: 10px;" @click="submitDialog">提交</el-button>
			</el-dialog>
		</div> 
	</div>
</template>

<script>
	export default {
		data() {
			return {
				tableData: [],
				studentData: [],
				val: null,
				dialogShow: false,
				item: {
					id: null,
					testNumber: '',
					testName: '',
					teacherName: '',
					date: null,
					place: '',
					isDelete: false,
				},
				item2: {
					student: '',
					score: null,
				}
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

			//点击编辑按钮
			handleEdit(index, data) { //拿原来的旧值
				this.$api.getRequest("/api/houtai/studentbytestid/" + data.id).then(
					resp => {
						this.studentData = resp.responseData
					}
				)
				this.val = this.studentData.length
				console.log(this.studentData.length)
				this.title = "登记成绩"
				// this.item2 = data
				this.index = index
				this.dialogShow = true
				console.log(this.item2)
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
					"date": null,
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
			add() {
				if (!this.item.testName ||
					!this.item.testNumber ||
					!this.item.teacherName) {
					this.$message.error("请填写完整信息！")
					return;
				}
				this.$api.postRequest("/api/houtai/test/", this.item).then(
					resp => {
						this.item = {
							"id": null,
							"testNumber": '',
					  "testName": '',
							"teacherName": '',
							"date": null,
							"place": '',
							"isDelete": false,
						}
						this.initList()
						this.dialogShow = false
					}
				)
			},
			edit() {
				let that = this
				if (!this.item.testName ||
					!this.item.testNumber ||
					!this.item.teacherName ||
					!this.item.date) {
					this.$message.error("请填写完整信息！")
					return;
				}
				this.$api.putRequest("/api/houtai/test", this.item).then(
					resp => {
						that.tableData[that.index] = that.item
						that.item = {
							"id": null,
							"testNumber": '',
							"testName": '',
							"teacherName": '',
							"date": null,
					  "place": '',
							"isDelete": false,
							// this.initCaiDanList()
						}
						that.dialogShow = false
					}
				)
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

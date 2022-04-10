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
	</div>
</template>

<script>
	export default {
		data() {
			return {
				tableData: [],
				key: '',
				item: {
					"id": null,
					"testName": '',
					"teacherName": '',
					"date": null,
					"place": '',
					"isDelete": false,
				},
				user: JSON.parse(window.sessionStorage.getItem("user")),
			}
		},
		mounted() {
			this.initList()
		},
		methods: {
			search() {
				this.tableData = []
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
				this.item=data
				this.$confirm('确认报名:[' + data.testName + ']的考试吗？',
						'请再次确认操作', {
							confirmButtonText: '确认',
							cancelButtonText: '取消',
							type: 'warning'
						},
					)
					
					.then(() => {
						// console.log(user.id);
						this.$api.postKeyValueRequest("/api/houtai/enroll", {
							"userId": this.user.id,
							"testId": this.item.id
						})
					})
				.catch(() => {
					this.$message({
						type: 'info',
						message: '取消报名成功',
					})
				})
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

<template>
	<div>
		<div style="margin-top: 15px;">
			<el-table :data="tableData" size="mini" border stripe style="width: 90%">
				<el-table-column prop="testNumber" label="考试编号" width="100" />
				<el-table-column prop="testName" label="考试名称" width="150" />
				<el-table-column prop="scoreByUserId" label="成绩" width="150" />

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
					"testNumber": '',
					"testName": '',
					"scoreByUserId": null ,
					"isDelete": false,
				},
				user: JSON.parse(window.sessionStorage.getItem("user")),


			}
		},
		mounted() {
			this.initList()
		},
		methods: {
			initList() {
				this.$api.getRequest("/api/houtai/scorelistbyuserid/" + this.user.id).then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			}
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

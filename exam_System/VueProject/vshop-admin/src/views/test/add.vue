<template>
	<div class="add">
		<table style="width: 100%;">
			<tr>
				<td style="width: 200px; text-align: center;margin-right: 15px;">
					<el-tag type="success">考试编号</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.testNumber"
					placeholder="请输入考试编号"></el-input>
				</td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center;margin-right: 15px;">
					<el-tag type="success">考试名称</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.testName"
					placeholder="请输入考试科目"></el-input>
				</td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center; margin-right: 15px;">
					<el-tag type="success">监考教师</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.teacherName"
						placeholder="请输入监考教师姓名"></el-input>
				</td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center; margin-right: 15px;">
					<el-tag type="success">地点</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.place"
					placeholder="考场"></el-input>
				</td>
			</tr>
		</table>
	</div>
	<div class="foot">
		<el-button size="small" @click="handleCancel">取消</el-button>
		<el-button type="primary" size="mini" @click="handleSubmit">确定</el-button>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				item: {
					id: null,
					testNumber: '',
					testName: '',
					teacherName: '',
					place: ''
				},
				addSuccessFlag:false,
			}
		},
		methods: {
			handleCancel() {
				this.$api.deleteRequest("/api/houtai/test/" + this.item.id)
				this.$router.replace("list")
			},
			handleSubmit() {
				this.addSuccessFlag=false
				this.$api.putRequest("/api/houtai/test", this.item).then((res) => {
					this.$router.replace("list")
					this.addSuccessFlag=true
				})
			}
		},
		mounted() {
			this.$api.postRequest("/api/houtai/test", this.item).then((res) => {
				this.item.id = res.responseData.id
			})
			this.addSuccessFlag=false
		},
		beforeUnmount() {
			if(!this.addSuccessFlag)
			this.$api.deleteRequest("/api/houtai/test/" + this.item.id)
		}
	}
</script>

<style>
	.title {
		width: 100%;
		height: 65px;
		/* border: 1px solid #26494B; */
		front-size: 42px;
		justify-content: center;
		display: flex;
	}

	.add {
		width: 100%;
		/* border: 1px solid #26494B; */
		margin: 20px 0px; 
	}

	.foot {
		width: 100%;
		justify-content: center;
		display: flex;
	}

	.upload-demo {
		width: 100%;
		border-radius: calc();

	}
</style>

<template>
	<!-- <div class="title">
		<h4 style="margin: auto 0px;">添加商品</h4>
	</div> -->
	<div class="add">
		<table style="width: 100%;">
			<tr>
				<td style="width: 200px; text-align: center;margin-right: 15px;">
					<el-tag type="success">品牌</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.pinPai"></el-input>
				</td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center;margin-right: 15px;">
					<el-tag type="success">名称</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.mingCheng"></el-input>
				</td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center; margin-right: 15px;">
					<el-tag type="success">编号</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.shangPinBianHao"
						placeholder="请输入12位商品编号"></el-input>
				</td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center; margin-right: 15px;">
					<el-tag type="success">价格</el-tag>
				</td>
				<td>
					<el-input size="mini" prefix-icon="el-icon-edit" v-model="item.jiaGe"></el-input>
				</td>
			</tr>
			<tr>
				<td style="width: 200px; text-align: center; margin-right: 15px; vertical-align:top ;">
					<el-tag type="success">图片</el-tag>
				</td>
				<td>
					<el-upload class="upload-demo" :action="uploadAction" :on-remove="handleRemove"
						:file-list="fileList" list-type="picture">
						<el-button size="small" type="primary">Click to upload</el-button>
						<template #tip>
							<div class="el-upload__tip">
								jpg/png files with a size less than 500kb
							</div>
						</template>
					</el-upload>
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
				uploadAction: '',
				fileList: [],
				item: {
					id: null,
					pinPai: '',
					mingCheng: '',
					shangPinBianHao: '',
					jiaGe: 0
				},
				addSuccessFlag:false,
			}
		},
		methods: {
			handleRemove(file, fileList) {
				this.$api.deleteRequest("/api/houtai/shangpintupian/" + file.response.responseData.id)
			},
			handleCancel() {
				this.$api.deleteRequest("/api/houtai/shangpin/" + this.item.id)
				this.$router.replace("list")
			},
			handleSubmit() {
				this.addSuccessFlag=false
				this.$api.putRequest("/api/houtai/shangpin", this.item).then((res) => {
					this.$router.replace("list")
					this.addSuccessFlag=true
				})
			}
		},
		mounted() {
			this.$api.postRequest("/api/houtai/shangpin", this.item).then((res) => {
				this.item.id = res.responseData.id
				this.uploadAction = "/api/houtai/shangpintupian/" + this.item.id
			})
			this.addSuccessFlag=false
		},
		beforeUnmount() {
			if(!this.addSuccessFlag)
			this.$api.deleteRequest("/api/houtai/shangpin/" + this.item.id)
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

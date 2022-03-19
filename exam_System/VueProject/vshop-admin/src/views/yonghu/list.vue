<template>
	<div>
		<div>
			<el-input ref="xsmc" v-model="key" size="mini" style="width: 200px; margin-left: 10px;"
				placeholder="请输入关键字..." prefix-icon="fa fa-search" @keydown.enter.native="search">
			</el-input>
			<el-button plain type="primary" size="mini" style="margin-left: 10px;" icon="fa fa-search" @click="search">
				查询</el-button>
		</div>
		<div style="margin-top: 15px;">
			<el-table :data="tableData" size="mini" border stripe style="width: 90%">
				<el-table-column prop="id" label="编号" width="80" />
				<el-table-column prop="yongHuMing" label="用户名" width="100" />
				<el-table-column prop="zhuCeShiJian" label="注册时间" width="150" />
				<el-table-column prop="shiFouJinYong" label="当前状态" width="150" />
				<el-table-column label="操作">
					<template #default="scope">
						<!-- <el-button  size="mini" type="success" @click="handleQiYong(scope.$index, scope.row)" 
						v-if="scope.row.shiFouJinYong=='已禁用'" icon="fa fa-circle-o"></el-button>
						<el-button plain size="mini" type="danger" @click="handleTingYong(scope.$index, scope.row)"
						v-if="scope.row.shiFouJinYong!='已禁用'" icon="fa fa-minus"></el-button> -->
						<el-switch v-model="panding[scope.row.id-1]" active-color="#d6d4d5" inactive-color="#aeb9d2"
							@click="handle(scope.$index, scope.row)"></el-switch>
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
				panding: []
			}
		},
		mounted() {
			this.initList()
		},
		methods: {
			search() {
				this.$api.getRequest("/api/houtai/yonghu/" + this.key).then(
					resp => {
						let dataList = []
						for (let i = 0; i < resp.responseData.length; i++) {
							let item = resp.responseData[i]
							if (item.shiFouJinYong) {
								item.shiFouJinYong = '已禁用'
								panding[i] = true
							} else {
								item.shiFouJinYong = '启用中'
								panding[i] = false
							}
							dataList.push(item)
						}
						this.tableData = dataList
					}
				)
			},
			initList() {
				// this.tableData = []
				this.$api.getRequest("/api/houtai/yonghu").then(
					resp => {
						let dataList = []
						for (let i = 0; i < resp.responseData.length; i++) {
							let item = resp.responseData[i]
							if (item.shiFouJinYong) {
								item.shiFouJinYong = '已禁用'
								this.panding[i] = true
							} else {
								item.shiFouJinYong = '启用中'
								this.panding[i] = false
							}
							dataList.push(item)
						}
						this.tableData = dataList
						console.log(this.panding);
					}
				)
			},

			// handleQiYong(index, data) { //拿原来的旧值
			// 	this.$api.postRequest("/api/houtai/yonghu/qiyong/" + data.id)
			// 	data.shiFouJinYong='启用中'
			// },

			// handleTingYong(index, data) {
			// 	this.$api.postRequest("/api/houtai/yonghu/tingyong/" + data.id)
			// 	data.shiFouJinYong='已禁用'
			// },
			handle(index, data) {
				if (data.shiFouJinYong == '已禁用') {
					this.$api.postRequest("/api/houtai/yonghu/qiyong/" + data.id)
					data.shiFouJinYong = '启用中'
					this.panding[data.id - 1] = false
				} else {
					this.$api.postRequest("/api/houtai/yonghu/tingyong/" + data.id)
					data.shiFouJinYong = '已禁用'
					this.panding[data.id - 1] = true
				}
			}
		}
	}
</script>

<style>
</style>

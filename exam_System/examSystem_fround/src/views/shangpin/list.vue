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
				<el-table-column prop="pinPai" label="品牌" width="100" />
				<el-table-column prop="mingCheng" label="名称" width="150" />
				<el-table-column prop="shangPinBianHao" label="商品编号" width="150" />
				<el-table-column prop="jiaGe" label="价格" width="150" />
				<el-table-column label="操作">
					<template #default="scope">
						<el-button plain size="mini" type="success" @click="handleXiangQing(scope.$index, scope.row)">详情
						</el-button>

					</template>
				</el-table-column>
			</el-table>
		</div>
		<div>
			<el-dialog v-model="dialogShow" width="80%" :title="商品详情">
				<div style="margin-bottom: 10px;">
					<table width="100%">
						<tr class='tr'>
							<td style="width:100px;color: #ef8a81;" >商品名称</td> <td>{{item.mingCheng}}</td>
						</tr>
						<tr class='tr'>
							<td style="width:100px;color: #ef8a81;">品牌</td> <td>{{item.pinPai}}</td>
							<td style="width:100px;color: #ef8a81;">商品编号</td> <td>{{item.shangPinBianHao}}</td>	
						</tr>
						<tr class='tr'>
							<td style="width:100px;color: #ef8a81;">商品价格</td> <td>{{item.jiaGe}}</td>
							<td style="width:100px;color: #ef8a81;">商品产地</td> <td>中国大陆</td>
						</tr>
					</table>
				</div>
				<div >
					<img :src ="img" class="imgStyle" v-for="(img,index) in this.item.imgList" :key="index"/>
				</div>
			</el-dialog>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				tableData: [],
				key: '',
				dialogShow: false,
				item:{
					id: null,
					imgList:[],
					pinPai: '',
					mingCheng: '',
					shangPinBianHao: '',
					jiaGe: null,
					isDelete:false
				}
			}
		},
		mounted() {
			this.initList()
		},
		methods: {
			search() {
				this.$api.getRequest("/api/houtai/shangpin/" + this.key).then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			},
			initList() {
				this.$api.getRequest("/api/houtai/shangpin").then(
					resp => {
						this.tableData = resp.responseData
					}
				)
			},

			handleXiangQing(index, data) { //点击进入详情页面
				this.dialogShow = true
				this.item=data
				this.$api.getRequest("/api/houtai/shangpintupian/"+this.item.id).then(
					resp => {
						this.item.imgList=resp.responseData
					}
				)
			}
		}
	}
</script>

<style>
	.tr{
		height: 50px;
		padding:50px;
		border-bottom: #A0AECB;
		
	}
	.imgStyle{
		width:70%;
		margin: 0px auto;
		display:flex;
	}
</style>

<template>
	<div style="border: 1px solid #b6c6df;width: 60% ">
		<div class="div">
			<div style="width: 100px;display: flex; justify-content: center;">
				<el-tag size="small" style="margin:2px auto;">员工</el-tag>
			</div>
			<el-select style="margin-left: :15px; width: 350px;" v-model="value" filterable placeholder="请选择员工" 
				size="mini" @change="selectedChangeHandle">
				<el-option v-for="item in options" :key="item.id" :label="item.zhenShiXingMing" :value="item.id">
				</el-option>
			</el-select>
		</div>
		<div class="div">
			<el-tag size="small" style="margin-left: 15px;" icon="fa fa-wpexplorer">角色</el-tag>
			<el-tree ref="tree" :data="data" show-checkbox default-expand-all node-key="id" highlight-current
				:props="defaultProps" style="margin-left:15px; width: 350px;" />
		</div>
		<div class="div">
			<el-button type="success" size="mini" style="margin-right: 40px;" @click="submite">分配</el-button>
			<el-button type="info" size="mini" @click="redo" >还原</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				options: [],
				value: '',
				data: [],
				defaultProps: {
					label: 'xianShiMingCheng',
				},
			}
		},
		created(){
			this.$api.getRequest("/api/system/config/juese").then((res)=>{
				this.data=res.responseData
			})	
			this.$api.getRequest("/api/system/config/yuangong").then((res)=>{
				this.options=res.responseData
			})
		},
		methods:{
			selectedChangeHandle(val){
				this.$refs.tree.setCheckedKeys([])
				this.$api.getRequest("/api/system/config/yuangongjuese/" + val).then((res)=>{
					let selectedData=[]
					for(let x in res.responseData){
						selectedData.push(res.responseData[x].id)
					}
					this.$refs.tree.setCheckedKeys(selectedData)
				})
			},
			submite(){
				if(this.value==''){
					this.$message.error("请选择员工后分配角色")
					return
				}
				let selectedData = this.$refs.tree.getCheckedKeys()
				this.$api.postKeyValueRequest("/api/system/config/yuangongjuese",{
					"yuanGongId":this.value,
					"jueSeIds":selectedData
				})
			},
			redo(){
				this.$refs.tree.setCheckedKeys([])
				
				if(this.value=='')return;
				
				this.$api.getRequest("/api/system/config/yuangongjuese/"+this.value).then((res)=>{
					let selectedData=[]
					for(let x in res.responseData){
						selectedData.push(res.responseData[x].id)
					}
					this.$refs.tree.setCheckedKeys(selectedData)
				})
			}
		}
	}
</script>

<style>
	.div {
		display: flex;
		justify-content: center;
		width: 500px;
		margin: 20px;
	}
</style>


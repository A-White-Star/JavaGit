<template>
	<div>
  <el-tabs v-model="activeName" style="width: 100%; min-height: 800px;" >
    <el-tab-pane label="角色管理" name="xx1" >
		<jsgl/>
	</el-tab-pane>
    <el-tab-pane label="菜单管理" name="xx2">
	<cdgl/>
	</el-tab-pane>
    <el-tab-pane label="角色菜单管理" name="xx3">
		<fp/>
	</el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
	 import jsgl from '../../components/syst/jueseguanli.vue'
	 import cdgl from '../../components/syst/caidanguanli.vue'
	 import fp from '../../components/syst/fenpei.vue'
	export default { 
		data(){
			return{
				activeName:'xx1'
			}
		},
		components:{
			jsgl,
			cdgl,
			fp
		}
	}
</script>

<style>
</style>

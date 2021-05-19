<template>
  <div>
    <div class="space-20"></div>
    <div class="row">
      <div class="col-xs-12">
        <div class="center">
          <span class="btn btn-app btn-sm btn-light no-hover">
            <span class="line-height-1 bigger-170 blue"> 518 </span>

            <br/>
            <span class="line-height-1 smaller-90"> 课程 </span>
          </span>

          <span class="btn btn-app btn-sm btn-yellow no-hover">
													<span class="line-height-1 bigger-170"> 32 </span>

													<br/>
													<span class="line-height-1 smaller-90"> 大章 </span>
												</span>

          <span class="btn btn-app btn-sm btn-pink no-hover">
													<span class="line-height-1 bigger-170"> 4 </span>

													<br/>
													<span class="line-height-1 smaller-90"> 小节 </span>
												</span>

          <span class="btn btn-app btn-sm btn-grey no-hover">
													<span class="line-height-1 bigger-170"> 23 </span>

													<br/>
													<span class="line-height-1 smaller-90"> 会员(万) </span>
												</span>

          <span class="btn btn-app btn-sm btn-success no-hover">
													<span class="line-height-1 bigger-170"> 7 </span>

													<br/>
													<span class="line-height-1 smaller-90"> 评论(万) </span>
												</span>

          <span class="btn btn-app btn-sm btn-primary no-hover">
													<span class="line-height-1 bigger-170"> 55 </span>

													<br/>
													<span class="line-height-1 smaller-90"> Contacts </span>
												</span>
        </div>





      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: 'welcome',
    data: function () {
      return {
        loginUser: {},
        imageCodeToken: ""
      }
    },
    mounted: function () {
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("welcome-sidebar");

    },

    methods: {}

  }
</script>
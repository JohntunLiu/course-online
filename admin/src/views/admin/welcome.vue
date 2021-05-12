<template>
  <div>
      <h1>hello tang</h1>
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

  methods: {

  }

}
</script>
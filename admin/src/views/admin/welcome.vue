<template>
  <div>
      <h1>hello tang</h1>
  </div>
</template>

<script>
export default {
  name: 'welcome',

  mounted: function () {

    this.$parent.activeSidebar("welcome-sidebar");

  },

  methods: {

  }

}
</script>
<template>

  <div>

    <p>

      <!-- Button trigger modal -->
      <button @click="list(1)" class="btn btn-white btn-default btn-round eye-protector-processed" style="transition: background-color 0.3s ease 0s; border-color: rgba(0, 0, 0, 0.35); color: rgb(0, 0, 0); background-color: rgb(193, 230, 198);">
        <i class="ace-icon fa fa-refresh " ></i>
        刷新
      </button>
    </p>

    <Pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8" ></Pagination>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>id</th>

            <th>相对路径</th>

            <th>文件名</th>

            <th>后缀</th>

            <th>大小</th>

            <th>用途</th>



            <th>已上传分片</th>

            <th>分片大小</th>

            <th>分片总数</th>

            <th>文件标识</th>

      </tr>
      </thead>

      <tbody>

      <tr v-for="file in files">
            <td>{{ file.id}}</td>
            <td>{{ file.path}}</td>
            <td>{{ file.name}}</td>
            <td>{{ file.suffix}}</td>
            <td>{{ file.size}}</td>
            <td>{{ FILE_USE | optionKV(file.use)}}</td>
            <td>{{ file.shardIndex}}</td>
            <td>{{ file.shardSize}}</td>
            <td>{{ file.shardTotal}}</td>
            <td>{{ file.key}}</td>

      </tr>

      </tbody>
    </table>
    </div>

</template>

<script>

  import Pagination from "../../components/pagination"
  export default {
    name: 'file-file',
    components: {Pagination},
    data: function () {
      return{
        file: {},
        files: [],
        FILE_USE: FILE_USE,
      }
    },

    mounted: function () {
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("file-file-sidebar");
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);

    },

    methods: {
      add() {
        let _this = this;
        _this.file = {};
        $("#form-modal").modal("show");
      },

      edit(file) {
        let _this = this;
        _this.file = $.extend({},file);
        $("#form-modal").modal("show");
      },

      /**
       * 点击【查询】
       * @param page
       */

      list(page) {
        let _this = this;
        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/file/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          Loading.hide();

          let resp = response.data;
          _this.files = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        });
      },

    }

  }
</script>
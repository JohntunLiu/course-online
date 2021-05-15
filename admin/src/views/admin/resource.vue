<template>

  <div>

    <p>
      <button @click="list(1)" class="btn btn-white btn-default btn-round eye-protector-processed" style="transition: background-color 0.3s ease 0s; border-color: rgba(0, 0, 0, 0.35); color: rgb(0, 0, 0); background-color: rgb(193, 230, 198);">
        <i class="ace-icon fa fa-refresh " ></i>
        刷新
      </button>
    </p>

    <div class="row">
      <div class="col-md-6">
        <textarea id="resource-textarea" class="form-control" v-model="resourceStr" name="resource" rows="10"></textarea>

        <br>
        <button id="save-btn" type="button" class="btn btn-primary" v-on:click="save()">
          保存
        </button>
      </div>
      <div class="col-md-6">
        <ul id="tree" class="ztree"></ul>
      </div>
    </div>

    <Pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8" ></Pagination>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>id</th>

            <th>名称</th>

            <th>页面</th>

            <th>请求</th>

            <th>父id</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>

      <tr v-for="resource in resources">
            <td>{{ resource.id}}</td>
            <td>{{ resource.name}}</td>
            <td>{{ resource.page}}</td>
            <td>{{ resource.request}}</td>
            <td>{{ resource.parent}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button @click="edit(resource)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button @click="del(resource.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

          </div>

        </td>
      </tr>

      </tbody>
    </table>

  </div>

</template>

<script>

  import Pagination from "../../components/pagination"
  export default {
    name: 'system-resource',
    components: {Pagination},
    data: function () {
      return{
        resource: {},
        resources: [],
        resourceStr: "",
      }
    },

    mounted: function () {
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("system-resource-sidebar");
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);

    },

    methods: {

      /**
       * 点击【查询】
       * @param page
       */

      list(page) {
        let _this = this;
        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {

          Loading.hide();

          let resp = response.data;
          _this.resources = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        });
      },

      /**
       * 点击【保存】
       * id有值时更新，无值时插入
       *
       */
      save() {
        let _this = this;

        // 保存校验
        if (Tool.isEmpty(_this.resourceStr)) {
          Toast.warning("资源不能为空!");
          return;
        }
        let json = JSON.parse(_this.resourceStr);


        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/save',
          json
        ).then((response) => {
          Loading.hide();

          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("保存成功");
          } else {
            Toast.warning(resp.message);
          }

        })
      },

      /**
       * 点击【删除】
       *
       */
      del(id) {
        let _this = this;

        Confirm.show("删除资源后不可恢复，确认删除？", function () {

                  Loading.show();
                  _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/delete/'+id,
                          _this.resource
                  ).then((response) => {
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                      _this.list(1);
                      Toast.success("删除成功！");
                    }
                  })
                }

        );
      }
    }

  }
</script>
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
        tree: {},
      }
    },

    mounted: function () {
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("system-resource-sidebar");
      let _this = this;
      _this.list();

    },

    methods: {

      /**
       * 点击【查询】
       * @param page
       */

      list() {
        let _this = this;
        Loading.show();

        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/resource/load-tree').then((res)=>{
          Loading.hide();
          let resp = res.data;
          _this.resources = resp.content;
          //初始化树
          _this.initTree();
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
      },

      /**
       * 初始资源树
       */
      initTree() {
        let _this = this;
        let setting = {
          data: {
            simpleData: {
              idKey: "id",
              pIdKey: "parent",
              rootPId: "",
              // enable: true
            }
          }
        };

        _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.resources);
        _this.zTree.expandAll(true);
      },
    }

  }
</script>
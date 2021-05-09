<template>

  <div>

    <p>

      <!-- Button trigger modal -->
      <button @click="add()" class="btn btn-white btn-default btn-round eye-protector-processed" style="transition: background-color 0.3s ease 0s; border-color: rgba(0, 0, 0, 0.35); color: rgb(0, 0, 0); background-color: rgb(193, 230, 198);">
        <i class="ace-icon fa fa-edit " ></i>
        新增
      </button>
      &nbsp;
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

        <th>操作</th>
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

        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button @click="edit(file)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button @click="del(file.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

          </div>

        </td>
      </tr>

      </tbody>
    </table>
    <!-- Modal -->
    <div  id="form-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" >表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">

                   <div class="form-group">
                     <label class="col-sm-2 control-label">相对路径</label>
                     <div class="col-sm-10">
                       <input v-model="file.path" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">文件名</label>
                     <div class="col-sm-10">
                       <input v-model="file.name" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">后缀</label>
                     <div class="col-sm-10">
                       <input v-model="file.suffix" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">大小</label>
                     <div class="col-sm-10">
                       <input v-model="file.size" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">用途</label>
                     <div class="col-sm-10">
                       <select v-model="file.use" class="form-control">
                         <option v-for="o in FILE_USE" v-bind:value="o.key">{{o.value}}</option>
                       </select>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">已上传分片</label>
                     <div class="col-sm-10">
                       <input v-model="file.shardIndex" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">分片大小</label>
                     <div class="col-sm-10">
                       <input v-model="file.shardSize" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">分片总数</label>
                     <div class="col-sm-10">
                       <input v-model="file.shardTotal" class="form-control">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">文件标识</label>
                     <div class="col-sm-10">
                       <input v-model="file.key" class="form-control">
                     </div>
                   </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button @click="save" type="button" class="btn btn-primary">保存</button>
          </div>
        </div>
      </div>
    </div>
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

      /**
       * 点击【保存】
       * id有值时更新，无值时插入
       *
       */
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
                || !Validator.require(_this.file.path, "相对路径")
                || !Validator.length(_this.file.path, "相对路径", 1, 100)
                || !Validator.length(_this.file.name, "文件名", 1, 100)
                || !Validator.length(_this.file.suffix, "后缀", 1, 10)
                || !Validator.length(_this.file.key, "文件标识", 1, 32)
        ) {
          return;
        }

        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/file/save',
                _this.file
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

        Confirm.show("删除文件后不可恢复，确认删除？", function () {

                  Loading.show();
                  _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/file/delete/'+id,
                          _this.file
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
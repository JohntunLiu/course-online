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
      <tr><#list fieldList as field>
          <#if field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="vod" >
            <th>${field.nameCn}</th>
          </#if>
        </#list>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>

      <tr v-for="${domain} in ${domain}s">
        <#list fieldList as field>
          <#if field.nameHump!="createdAt" && field.nameHump!="updatedAt"&& field.nameHump!="vod">
            <#if field.enums>
            <td>{{ ${field.enumsConst} | optionKV(${domain}.${field.nameHump})}}</td>
            <#else >
            <td>{{ ${domain}.${field.nameHump }}}</td>
            </#if>
          </#if>
        </#list>

        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button @click="edit(${domain})" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button @click="del(${domain}.id)" class="btn btn-xs btn-danger">
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

              <#list fieldList as field>
               <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt"&& field.nameHump!="vod">
                 <#if field.enums>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">${field.nameCn}</label>
                     <div class="col-sm-10">
                       <select v-model="${domain}.${field.nameHump}" class="form-control">
                         <option v-for="o in ${field.enumsConst}" v-bind:value="o.key">{{o.value}}</option>
                       </select>
                     </div>
                   </div>
                 <#else>
                   <div class="form-group">
                     <label class="col-sm-2 control-label">${field.nameCn}</label>
                     <div class="col-sm-10">
                       <input v-model="${domain}.${field.nameHump}" class="form-control">
                     </div>
                   </div>
                 </#if>
                </#if>
              </#list>
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
    name: '${module}-${domain}',
    components: {Pagination},
    data: function () {
      return{
        ${domain}: {},
        ${domain}s: [],
        <#list fieldList as field>
        <#if field.enums>
        ${field.enumsConst}: ${field.enumsConst},
        </#if>
        </#list>
      }
    },

    mounted: function () {
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("${module}-${domain}-sidebar");
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);

    },

    methods: {
      add() {
        let _this = this;
        _this.${domain} = {};
        $("#form-modal").modal("show");
      },

      edit(${domain}) {
        let _this = this;
        _this.${domain} = $.extend({},${domain});
        $("#form-modal").modal("show");
      },

      /**
       * 点击【查询】
       * @param page
       */

      list(page) {
        let _this = this;
        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          Loading.hide();

          let resp = response.data;
          _this.${domain}s = resp.content.list;
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
                <#list fieldList as field>
                <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
                <#if !field.nullAble>
                || !Validator.require(_this.${domain}.${field.nameHump}, "${field.nameCn}")
                </#if>
                <#if (field.length > 0)>
                || !Validator.length(_this.${domain}.${field.nameHump}, "${field.nameCn}", 1, ${field.length?c})
                </#if>
                </#if>
                </#list>
        ) {
          return;
        }

        Loading.show();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/save',
                _this.${domain}
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

        Confirm.show("删除${tableNameCn}后不可恢复，确认删除？", function () {

                  Loading.show();
                  _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/delete/'+id,
                          _this.${domain}
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
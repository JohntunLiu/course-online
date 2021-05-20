<template>
  <div>
    <div class="space-20"></div>
    <div class="row">
      <div class="col-xs-12">
        <div class="text-center">
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
													<span class="line-height-1 smaller-90"> 报名(万) </span>
												</span>

          <span class="btn btn-app btn-sm btn-primary no-hover">
													<span class="line-height-1 bigger-170"> 55 </span>

													<br/>
													<span class="line-height-1 smaller-90"> 评论(万) </span>
												</span>
        </div>
        <div class="space-12"></div>

        <div class="hr hr2 hr-double"></div>

        <div class="space-12"></div>


      </div>

      <div class="col-sm-12 infobox-container ">
        <div class="infobox infobox-green">
          <div class="infobox-icon">
            <i class="ace-icon fa fa-comments"></i>
          </div>

          <div class="infobox-data">
            <span class="infobox-data-number">32</span>
            <div class="infobox-content">新增评论</div>
          </div>

          <div class="stat stat-success">8%</div>
        </div>

        <div class="infobox infobox-blue">
          <div class="infobox-icon">
            <i class="ace-icon fa fa-twitter"></i>
          </div>

          <div class="infobox-data">
            <span class="infobox-data-number">11</span>
            <div class="infobox-content">新注册会员</div>
          </div>

          <div class="badge badge-success">
            +32%
            <i class="ace-icon fa fa-arrow-up"></i>
          </div>
        </div>

        <div class="infobox infobox-pink">
          <div class="infobox-icon">
            <i class="ace-icon fa fa-shopping-cart"></i>
          </div>

          <div class="infobox-data">
            <span class="infobox-data-number">8</span>
            <div class="infobox-content">课程报名</div>
          </div>
          <div class="stat stat-important">4%</div>
        </div>

        <div class="space-12"></div>

        <div class="hr hr2 hr-double"></div>

        <div class="space-12"></div>

      </div>

      <div class="col-sm-12">
        <div class="widget-box transparent">
          <div class="widget-header widget-header-flat">
            <h4 class="widget-title lighter">
              <i class="ace-icon fa fa-signal"></i>
              销售量
            </h4>

          </div>

          <div class="widget-body">
            <div class="widget-main padding-4">
              <div id="sales-charts"></div>
            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.widget-box -->
        <div class="space-12"></div>

        <div class="hr hr2 hr-double"></div>

        <div class="space-12"></div>
      </div><!-- /.col -->

      <div class="col-sm-6">
        <div class="widget-box transparent">
          <div class="widget-header widget-header-flat">
            <h4 class="widget-title lighter">
              <i class="ace-icon fa fa-star orange"></i>
              课程销售排名
            </h4>
          </div>

          <div class="widget-body">
            <div class="widget-main no-padding">
              <table class="table table-bordered table-striped">
                <thead class="thin-border-bottom">
                <tr>
                  <th>
                    <i class="ace-icon fa fa-caret-right blue"></i>课程名称
                  </th>

                  <th>
                    <i class="ace-icon fa fa-caret-right blue"></i>价格
                  </th>

                  <th class="hidden-480">
                    <i class="ace-icon fa fa-caret-right blue"></i>购买数
                  </th>
                </tr>
                </thead>

                <tbody>
                <tr>
                  <td>internet.com</td>

                  <td>
                    <small>
                      <s class="red">$29.99</s>
                    </small>
                    <b class="green">$19.99</b>
                  </td>

                  <td class="hidden-480">
                    <span class="label label-success arrowed arrowed-right">6745</span>
                  </td>
                </tr>

                <tr>
                  <td>online.com</td>

                  <td>
                    <b class="blue">$16.45</b>
                  </td>

                  <td class="hidden-480">
                    <span class="label label-success arrowed arrowed-right">6745</span>

                  </td>
                </tr>

                <tr>
                  <td>newnet.com</td>

                  <td>
                    <b class="blue">$15.00</b>
                  </td>

                  <td class="hidden-480">
                    <span class="label label-warning arrowed arrowed-right">6745</span>
                  </td>
                </tr>

                <tr>
                  <td>web.com</td>

                  <td>
                    <small>
                      <s class="red">$24.99</s>
                    </small>
                    <b class="green">$19.95</b>
                  </td>

                  <td class="hidden-480">
                    <span class="label label-warning arrowed arrowed-right">6745</span>

                  </td>
                </tr>

                <tr>
                  <td>domain.com</td>

                  <td>
                    <b class="blue">$12.00</b>
                  </td>

                  <td class="hidden-480">
                    <span class="label label-warning arrowed arrowed-right">6745</span>
                  </td>
                </tr>
                </tbody>
              </table>
            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.widget-box -->
      </div><!-- /.col -->


      <div class="col-sm-6">
        <div class="widget-box transparent">
          <div class="widget-header widget-header-flat">
            <h4 class="widget-title lighter">
              <i class="ace-icon fa fa-star orange"></i>
              课程销售占比
            </h4>
          </div>

          <div class="widget-body">
            <div class="widget-main">
              <div id="piechart-placeholder"></div>


            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.widget-box -->
      </div><!-- /.col -->


    </div><!-- /.row -->

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
      let _this = this;
      _this.drawSaleChart();
      _this.drawPieChart();

    },

    methods: {
      drawSaleChart() {
        let d1 = [];
        for (let i = 0; i < 30; i++) {
          d1.push([i + 1, 2000 + Math.floor((Math.random() * 100) + 1)]);
        }

        let d2 = [];
        for (let i = 0; i < 30; i++) {
          d2.push([i + 1, 1900 + Math.floor((Math.random() * 100) + 1)]);
        }

        let sales_charts = $('#sales-charts').css({'width': '100%', 'height': '220px'});

        $.plot("#sales-charts", [
          {label: "最近30天", data: d1},
          {label: "上一周期", data: d2},
        ], {
          hoverable: true,
          shadowSize: 0,
          series: {
            lines: {show: true},
            points: {show: true}
          },
          xaxis: {
            tickLength: 0
          },
          yaxis: {
            tickLength: 0

          },
          grid: {
            backgroundColor: {colors: ["#fff", "#fff"]},
            borderWidth: 1,
            borderColor: '#555'
          }
        });
      },
      drawPieChart() {
        let placeholder = $('#piechart-placeholder').css({'width': '90%', 'min-height': '190px'});
        let data = [
          {label: "Java", data: 38.7, color: "#68BC31"},
          {label: "Python", data: 24.5, color: "#2091CF"},
          {label: "Android", data: 8.2, color: "#AF4E96"},
          {label: "其他", data: 28.6, color: "#DA5430"},
        ];
        $.plot(placeholder, data, {
          series: {
            pie: {
              show: true,
              tilt: 0.8,
              highlight: {
                opacity: 0.25
              },
              stroke: {
                color: '#fff',
                width: 2
              },
              startAngle: 2
            }
          },
          legend: {
            show: true,
            labelBoxBorderColor: null,
            margin: [-30, 15]
          }
          ,
          grid: {
            hoverable: true,
            clickable: true
          }
        })
      },

    },


  }
</script>
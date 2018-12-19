//package com.eps.Flink
//
//import java.sql.Types
//
//import org.apache.flink.api.java.io.jdbc.{JDBCInputFormat, JDBCOutputFormat}
//import org.apache.flink.api.scala.ExecutionEnvironment
//import org.apache.flink.table.api.TableEnvironment
//import org.apache.flink.table.sinks.CsvTableSink
//import org.apache.flink.api.scala._
//import org.apache.flink.streaming.api.scala._
//import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
//import org.apache.flink.configuration.Configuration
//import org.apache.flink.types.Row
//import org.apache.flink.api.java.typeutils.RowTypeInfo
//import org.apache.flink.api.common.typeinfo.BasicTypeInfo
//import java.sql.Types
//
//import scala.collection.immutable.{Queue, Stack}
//import scala.collection.mutable
//import scala.collection.mutable.{ArrayBuffer, ListBuffer}
//
//object Report {
//
//  case class Mark(col1:String,col2:String,col3:String,col4:String)
//
//  val driver = "com.mysql.jdbc.Driver"
//  val url = "jdbc:mysql://168.33.222.87:3307/report"
//  val username = "lj"
//  val password = "lj123456"
//
//  def main(args: Array[String]): Unit = {
//
//      //本地测试
//      //val Array(SourceHdfsPath, reportOutputHdfsPath, startDate,deadline,oracle_jdbc_url) = args                      //输入参数（jar包部署测试时打开）
//      val startDate = "20181028"                                                                                        //开始日期
//      val endDate = "20181028"                                                                                          //截止日期
//      val handleDate = "20181028"                                                                                       //处理日期
//      //val reportOutputHdfsPath = s"hdfs://192.168.20.10:9000/data/test/pos/"                                           //报表输出位置: "hdfs://master.hadoop:9000/data/test/"
//      val reportOutputHdfsPath = s"C:\\Users\\zhangliming\\Desktop\\YBS\\Item\\XinRuiTai\\XinRuiTaiFenRunBaoBiao\\XinRuiTaiYueBaoBiao\\XinRuiTaiYueBaoBiao\\src\\main\\resources\\tables"
//      val oracle_jdbc_url = "jdbc:oracle:thin:scott/tiger@168.33.222.87:1521:orcl"
//      val oracle_tableList = "YBS_SETT_ZHILIAN_20181121#PPBANKDS#SPECIAL_MER_REPORT3"                                    //小写表名需要加""
//      val merchant_list = "212#123"
//      val merchant_Lists = "'"+s"${merchant_list}".split("#").mkString("','")+"'"
//      val oracle_tables = oracle_tableList.split("#")
//
//      /**
//        * ExecutionEnvironment            //用于批处理
//        * StreamExecutionEnvironment      //用于流出来
//        *
//        * TableEnvironment                //
//        * BatchTableEnvironment           //
//        *
//        * */
//      //初始化Flink运行环境
//      val env = ExecutionEnvironment.getExecutionEnvironment
//      val tavleEvn = TableEnvironment.getTableEnvironment(env)
//
//
////      /**
////        * 基于本地集合的source
////        * 在flink最常见的创建DataSet方式有三种。
////        * 1.使用env.fromElements()，这种方式也支持Tuple，自定义对象等复合形式。
////        * 2.使用env.fromCollection(),这种方式支持多种Collection的具体类型
////        * 3.使用env.generateSequence()方法创建基于Sequence的DataSet
////        * */
////      import org.apache.flink.api.scala._
////      //0.用element创建DataSet(fromElements)
////      val ds0: DataSet[String] = env.fromElements("spark", "flink")
////      ds0.print()
////      //1.用Tuple创建DataSet(fromElements)
////      val ds1: DataSet[(Int, String)] = env.fromElements((1, "spark"), (2, "flink"))
////      ds1.print()
////      //2.用Array创建DataSet
////      val ds2: DataSet[String] = env.fromCollection(Array("spark", "flink"))
////      ds2.print()
////      //3.用ArrayBuffer创建DataSet
////      val ds3: DataSet[String] = env.fromCollection(ArrayBuffer("spark", "flink"))
////      ds3.print()
////      //4.用List创建DataSet
////      val ds4: DataSet[String] = env.fromCollection(List("spark", "flink"))
////      ds4.print()
////      //5.用List创建DataSet
////      val ds5: DataSet[String] = env.fromCollection(ListBuffer("spark", "flink"))
////      ds5.print()
////      //6.用Vector创建DataSet
////      val ds6: DataSet[String] = env.fromCollection(Vector("spark", "flink"))
////      ds6.print()
////      //7.用Queue创建DataSet
////      val ds7: DataSet[String] = env.fromCollection(Queue("spark", "flink"))
////      ds7.print()
////      //8.用Stack创建DataSet
////      val ds8: DataSet[String] = env.fromCollection(Stack("spark", "flink"))
////      ds8.print()
////      //9.用Stream创建DataSet（Stream相当于lazy List，避免在中间过程中生成不必要的集合）
////      val ds9: DataSet[String] = env.fromCollection(Stream("spark", "flink"))
////      ds9.print()
////      //10.用Seq创建DataSet
////      val ds10: DataSet[String] = env.fromCollection(Seq("spark", "flink"))
////      ds10.print()
////      //11.用Set创建DataSet
////      val ds11: DataSet[String] = env.fromCollection(Set("spark", "flink"))
////      ds11.print()
////      //12.用Iterable创建DataSet
////      val ds12: DataSet[String] = env.fromCollection(Iterable("spark", "flink"))
////      ds12.print()
////      //13.用ArraySeq创建DataSet
////      val ds13: DataSet[String] = env.fromCollection(mutable.ArraySeq("spark", "flink"))
////      ds13.print()
////      //14.用ArrayStack创建DataSet
////      val ds14: DataSet[String] = env.fromCollection(mutable.ArrayStack("spark", "flink"))
////      ds14.print()
////      //15.用Map创建DataSet
////      val ds15: DataSet[(Int, String)] = env.fromCollection(Map(1 -> "spark", 2 -> "flink"))
////      ds15.print()
////      //16.用Range创建DataSet
////      val ds16: DataSet[Int] = env.fromCollection(Range(1, 9))
////      ds16.print()
////      //17.用fromElements创建DataSet
////      val ds17: DataSet[Long] =  env.generateSequence(1,9)
////      ds17.print()
////
////      /**
////        * 基于文件的source（File-based-source）
////        * flink支持多种存储设备上的文件，包括本地文件，hdfs文件，alluxio文件等。
////        * flink支持多种文件的存储格式，包括text文件，CSV文件等。
////        * */
////      //1.读取本地文本文件,本地文件以file://开头
////      val ds18: DataSet[String] = env.readTextFile("file:///Applications/flink-1.1.3/README.txt")
////      ds18.print()
////
////      //2.读取hdfs文本文件，hdfs文件以hdfs://开头,不指定master的短URL
////      val ds19: DataSet[String] = env.readTextFile("hdfs:///input/flink/README.txt")
////      ds19.print()
////
////      //3.读取hdfs CSV文件,转化为tuple
////      val path = "hdfs://qingcheng11:9000/input/flink/sales.csv"
////      val ds20 = env.readCsvFile[(String, Int, Int, Double)](
////        filePath = path,          //hdfs地址
////        lineDelimiter = "\n",
////        fieldDelimiter = ",",
////        lenient = false,
////        ignoreFirstLine = true,
////        includedFields = Array(0, 1, 2, 3))
////      ds20.print()
////
////      //4.读取hdfs CSV文件,转化为case class
////      case class Sales(transactionId: String, customerId: Int, itemId: Int, amountPaid: Double)
////      val ds21 = env.readCsvFile[Sales](
////        filePath = path,              //hdfs地址
////        lineDelimiter = "\n",
////        fieldDelimiter = ",",
////        lenient = false,
////        ignoreFirstLine = true,
////        includedFields = Array(0, 1, 2, 3),
////        pojoFields = Array("transactionId", "customerId", "itemId", "amountPaid")
////      )
////      ds21.print()
////
////      /**
////        * 基于文件的source（遍历目录）
////        * flink支持对一个文件目录内的所有文件，包括所有子目录中的所有文件的遍历访问方式。
////        * */
////      // create a configuration object
////      val parameters = new Configuration
////      // set the recursive enumeration parameter
////      parameters.setBoolean("recursive.file.enumeration", true)
////      // pass the configuration to the data source
////      val ds22 = env.readTextFile("hdfs:///input/flink").withParameters(parameters)
////      ds22.print()
//
//      /**
//        *基于关系型数据库RDBMS的Source
//        *
//        *
//        * */
//
//      selectAllFields(env)
//      tavleEvn.registerDataSet("aaa",selectAllFields(env))
//      val table = tavleEvn.sqlQuery(
//        s"""
//           |select AREA from aaa
//         """.stripMargin)
//      println(">>>>>>>>>>>>>>>>>>>>>>>" + table)
//
//      /**
//        * 基于No-SQL的Source
//        *
//        *
//        * */
//
//  }//main() end
//     /**
//       *  Method of querying the fields from relational databases
//       * @param env
//       *
//       * */
//    def selectAllFields(env: ExecutionEnvironment):DataSet[Row] = {
//        val inputBuilder = JDBCInputFormat.buildJDBCInputFormat()
//          .setDrivername(driver)
//          .setDBUrl(url)
//          .setUsername(username)
//          .setPassword(password)
//          .setQuery("select TERM_ID,AREA,MANAGER from terminal_query201806")
//          // 这里第一个字段类型写int会报类型转换异常。
//          .setRowTypeInfo(new RowTypeInfo(
//                      BasicTypeInfo.STRING_TYPE_INFO,
//                      BasicTypeInfo.STRING_TYPE_INFO,
//                      BasicTypeInfo.STRING_TYPE_INFO))
//        val source = env.createInput(inputBuilder.finish)
//        source.print()
//        source
//    }
//}

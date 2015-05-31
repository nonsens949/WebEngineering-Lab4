
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object winner extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[models.JeopardyGame,Boolean,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(game: models.JeopardyGame, success: Boolean, uuid: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import play.i18n._
import helper._

Seq[Any](format.raw/*1.61*/("""
"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("winner-page", "Gewinnanzeige")/*5.38*/ {_display_(Seq[Any](format.raw/*5.40*/("""      
      """),format.raw/*6.7*/("""<!-- Content -->
      <div role="main">
		"""),_display_(/*8.4*/gameInfo(game.getLeader(), game.getSecond(), game.getMaxQuestions(), game.isHumanPlayer(game.getLeader().getUser()))),format.raw/*8.120*/("""
		"""),format.raw/*9.3*/("""<section id="twitter_result">
			<h2 id="twitter_result_heading class="accessibility">
			<p class="user-info">
				"""),_display_(/*12.6*/Messages/*12.14*/.get("twitter.uuid")),format.raw/*12.34*/("""
				"""),_display_(/*13.6*/if(success)/*13.17*/{_display_(Seq[Any](format.raw/*13.18*/("""
					"""),_display_(/*14.7*/uuid),format.raw/*14.11*/(""" """),_display_(/*14.13*/Messages/*14.21*/.get("twitter.success")),format.raw/*14.44*/("""
				""")))}/*15.6*/else/*15.10*/{_display_(Seq[Any](format.raw/*15.11*/("""
					"""),_display_(/*16.7*/Messages/*16.15*/.get("twitter.fail")),format.raw/*16.35*/("""
				""")))}),format.raw/*17.6*/("""		
			"""),format.raw/*18.4*/("""</p>
			</h2>
		</section>
         <section id="newgame" aria-labelledby="newgameheading">
             <h2 id="newgameheading" class="accessibility">"""),_display_(/*22.61*/Messages/*22.69*/.get("quizover.newgame")),format.raw/*22.93*/("""</h2>
             """),_display_(/*23.15*/helper/*23.21*/.form(routes.GameController.newGame())/*23.59*/ {_display_(Seq[Any](format.raw/*23.61*/("""
             """),format.raw/*24.14*/("""<input name="newgame" id="new_game" class="orangelink contentlink" type="submit" value=""""),_display_(/*24.103*/Messages/*24.111*/.get("quizover.newgame")),format.raw/*24.135*/("""" />
             """)))}),format.raw/*25.15*/("""
"""),format.raw/*26.1*/("""<!--          	 <a class="orangelink contentlink" id="new_game" href=""""),_display_(/*26.72*/routes/*26.78*/.GameController.newGame()),format.raw/*26.103*/("""">"""),_display_(/*26.106*/Messages/*26.114*/.get("quizover.newgame")),format.raw/*26.138*/("""</a>
 -->         </section>
      </div>
""")))}/*29.2*/ {_display_(Seq[Any](format.raw/*29.4*/("""
		"""),format.raw/*30.3*/("""<script type="text/javascript">
        //<![CDATA[
           $(document).ready(function()"""),format.raw/*32.40*/("""{"""),format.raw/*32.41*/("""
         	   """),format.raw/*33.14*/("""if(supportsLocalStorage())"""),format.raw/*33.40*/("""{"""),format.raw/*33.41*/("""
         		   """),format.raw/*34.15*/("""localStorage["lastGame"] = new Date().getTime();
         	   """),format.raw/*35.14*/("""}"""),format.raw/*35.15*/("""
           """),format.raw/*36.12*/("""}"""),format.raw/*36.13*/(""");
        //]]>
        </script>  
""")))}))}
  }

  def render(game:models.JeopardyGame,success:Boolean,uuid:String): play.twirl.api.HtmlFormat.Appendable = apply(game,success,uuid)

  def f:((models.JeopardyGame,Boolean,String) => play.twirl.api.HtmlFormat.Appendable) = (game,success,uuid) => apply(game,success,uuid)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun May 31 14:42:41 CEST 2015
                  SOURCE: C:/Users/bit/Desktop/WebEngineering-Lab4/app/views/winner.scala.html
                  HASH: e5bf149793f949b6ec9336ad60141b97b4b153e9
                  MATRIX: 752->1|933->60|961->101|989->104|1033->140|1072->142|1112->156|1183->202|1320->318|1350->322|1496->442|1513->450|1554->470|1587->477|1607->488|1646->489|1680->497|1705->501|1734->503|1751->511|1795->534|1820->541|1833->545|1872->546|1906->554|1923->562|1964->582|2001->589|2035->596|2218->752|2235->760|2280->784|2328->805|2343->811|2390->849|2430->851|2473->866|2590->955|2608->963|2654->987|2705->1007|2734->1009|2832->1080|2847->1086|2894->1111|2925->1114|2943->1122|2989->1146|3053->1192|3092->1194|3123->1198|3244->1291|3273->1292|3316->1307|3370->1333|3399->1334|3443->1350|3534->1413|3563->1414|3604->1427|3633->1428
                  LINES: 26->1|30->1|31->4|32->5|32->5|32->5|33->6|35->8|35->8|36->9|39->12|39->12|39->12|40->13|40->13|40->13|41->14|41->14|41->14|41->14|41->14|42->15|42->15|42->15|43->16|43->16|43->16|44->17|45->18|49->22|49->22|49->22|50->23|50->23|50->23|50->23|51->24|51->24|51->24|51->24|52->25|53->26|53->26|53->26|53->26|53->26|53->26|53->26|56->29|56->29|57->30|59->32|59->32|60->33|60->33|60->33|61->34|62->35|62->35|63->36|63->36
                  -- GENERATED --
              */
          
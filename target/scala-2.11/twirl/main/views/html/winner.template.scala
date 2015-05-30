
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
					"""),format.raw/*14.6*/("""uuid """),_display_(/*14.12*/Messages/*14.20*/.get("twitter.success")),format.raw/*14.43*/("""
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
                  DATE: Sat May 30 10:22:28 CEST 2015
                  SOURCE: C:/Users/Anja/Documents/Studium/Web-Engineering/Uebung/Uebung4/we15-lab4/app/views/winner.scala.html
                  HASH: b7b711c49f8587d85afc4e2b3c6d8e2287b9f908
                  MATRIX: 752->1|933->60|961->101|989->104|1033->140|1072->142|1112->156|1183->202|1320->318|1350->322|1496->442|1513->450|1554->470|1587->477|1607->488|1646->489|1680->496|1713->502|1730->510|1774->533|1799->540|1812->544|1851->545|1885->553|1902->561|1943->581|1980->588|2014->595|2197->751|2214->759|2259->783|2307->804|2322->810|2369->848|2409->850|2452->865|2569->954|2587->962|2633->986|2684->1006|2713->1008|2811->1079|2826->1085|2873->1110|2904->1113|2922->1121|2968->1145|3032->1191|3071->1193|3102->1197|3223->1290|3252->1291|3295->1306|3349->1332|3378->1333|3422->1349|3513->1412|3542->1413|3583->1426|3612->1427
                  LINES: 26->1|30->1|31->4|32->5|32->5|32->5|33->6|35->8|35->8|36->9|39->12|39->12|39->12|40->13|40->13|40->13|41->14|41->14|41->14|41->14|42->15|42->15|42->15|43->16|43->16|43->16|44->17|45->18|49->22|49->22|49->22|50->23|50->23|50->23|50->23|51->24|51->24|51->24|51->24|52->25|53->26|53->26|53->26|53->26|53->26|53->26|53->26|56->29|56->29|57->30|59->32|59->32|60->33|60->33|60->33|61->34|62->35|62->35|63->36|63->36
                  -- GENERATED --
              */
          
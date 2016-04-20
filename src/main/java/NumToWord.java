import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Object;
import java.lang.String;
import spark.ModelAndView;
import static java.lang.System.out;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class NumToWord {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/detector", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/detector.vtl");

      String number = request.queryParams("number");
      String finalString = calculateScore(number);

      model.put("finalString", finalString);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String calculateScore(String number) {

    HashMap<String, String> maps = new HashMap<String, String>();
      maps.put("0", "");
      maps.put("1", "one");
      maps.put("2", "two");
      maps.put("3", "three");
      maps.put("4", "four");
      maps.put("5", "five");
      maps.put("6", "six");
      maps.put("7", "seven");
      maps.put("8", "eight");
      maps.put("9", "nine");
    HashMap<String, String> mapstens = new HashMap<String, String>();
      mapstens.put("0", "");
      mapstens.put("1", "ten");
      mapstens.put("2", "twenty");
      mapstens.put("3", "thirty");
      mapstens.put("4", "fourty");
      mapstens.put("5", "fifty");
      mapstens.put("6", "sixty");
      mapstens.put("7", "seventy");
      mapstens.put("8", "eighty");
      mapstens.put("9", "ninety");
    HashMap<String, String> mapTeens = new HashMap<String, String>();
      mapTeens.put("11", "eleven");
      mapTeens.put("12", "twelve");
      mapTeens.put("13", "thirteen");
      mapTeens.put("14", "fourteen");
      mapTeens.put("15", "fifteen");
      mapTeens.put("16", "sixteen");
      mapTeens.put("17", "seventeen");
      mapTeens.put("18", "eighteen");
      mapTeens.put("19", "nineteen");
      int temp = 0;
    String comma = addCommas(number);
    int numberOfDigits = number.length();
    String returnValue= " ";
    int tempOftemp = numberOfDigits;
    String[] arrayOfSections = comma.split(",");
    int flag = 0;
  for(int j = 0; j<arrayOfSections.length; j++){
    flag = 0;
    for(int i=3; i>0; i--){
      int numberAsInteger = Integer.parseInt(arrayOfSections[j]);

      if(arrayOfSections[j].length() == 3){
        if(i%3==0){
          temp = numberAsInteger;
          temp = temp - numberAsInteger % 100;
          temp /=100;
          String tempS = Integer.toString(temp);
          returnValue+= maps.get(tempS);
          if(arrayOfSections[j].charAt(0) != '0'){
            returnValue += " Hundred ";
          }
        }
      }
      if(arrayOfSections[j].length() > 1){
        if (i%3==2) {
          temp = numberAsInteger % 100;
          temp = temp - numberAsInteger % 10;
          temp/=10;
          String tempS = Integer.toString(temp);
          String checkTeens;
          StringBuilder sb = new StringBuilder();
          sb.append(arrayOfSections[j].charAt(1));
          sb.append(arrayOfSections[j].charAt(2));
          checkTeens = sb.toString();

          switch(checkTeens){
            case "11":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "12":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "13":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "14":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "15":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "16":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "17":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "18":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            case "19":
              flag = 1;
              returnValue+= mapTeens.get(checkTeens);
              break;
            default:
              returnValue+= mapstens.get(tempS);
              break;
            }
            returnValue+= " ";

        }
      }
        if ((i%3==1) && flag == 0) {
          temp = numberAsInteger % 10;
          String tempS = Integer.toString(temp);
          returnValue+= maps.get(tempS);
          returnValue+= " ";

        }
    }

    if(j == arrayOfSections.length-2){
      returnValue+=" Thousand ";
    }
    if(j == arrayOfSections.length-3){
      returnValue+=" Million ";
    }
    if(j == arrayOfSections.length-4){
      returnValue+=" Billion ";
    }
    if(j == arrayOfSections.length-5){
      returnValue+=" Trillion ";
    }
    if(j == arrayOfSections.length-6){
      returnValue+=" Quadrillion ";
    }
    if(j == arrayOfSections.length-7){
      returnValue+=" Quintillion ";
    }
  }
    return returnValue;
  }
  public static String addCommas (String x){
     String regex = "(\\d)(?=(\\d{3})+$)";
    return x.replaceAll(regex, "$1,");
  }
}

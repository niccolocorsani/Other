package it.lascaux.askme.askmedesk_selenium_tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

////Selenide non supporta try/catch
public class SelenideUtility {


    private Condition clickable;

    public SelenideUtility() {
        //https://selenide.gitbooks.io/user-guide/content/en/selenide-api/condition.html
        clickable = Condition.and("can be clicked", visible, enabled); //// and significa è come l'and logico && nelle condizioni


    }


    public void clickChosenContainerElement(String selectId, int dropDownElementNumber) {
        ////Ogni volta che ho la tendina, poichè i campi devono essere visualizzati (non nascosti) e poichè devo ottenere il chose-container specifico
        //// che non è identificato in alcun modo procedo come segue
        SelenideElement primoScrollDownContainer = $$("label").findBy(Condition.text(selectId)).shouldBe(Condition.exist, Condition.visible);
        SelenideElement formGroupPrimo = primoScrollDownContainer.parent().shouldBe(Condition.exist);
        SelenideElement chosenContainerPrimo = formGroupPrimo.find(".chosen-container-single").shouldBe(Condition.exist, Condition.visible);  ////così effettivamente trova il figlio di from-group con tali caratteristiche
        // if(chosenContainerPrimo != null)
      //  chosenContainerPrimo.doubleClick();
        ElementsCollection liElementsPrimaTendina = formGroupPrimo.findAll("li");  //// trova tutti gli elementi figli di formGroupPrimo che hanno tag li
        liElementsPrimaTendina.get(dropDownElementNumber).shouldBe(Condition.exist, Condition.visible);

        //  liElementsPrimaTendina.get(dropDownElementNumber).shouldBe(visible);
//        liElementsPrimaTendina.get(dropDownElementNumber).shouldBe(not(visible));

        chosenContainerPrimo.click();
        System.out.println(selectId);

        while (!liElementsPrimaTendina.get(dropDownElementNumber).isEnabled() || !liElementsPrimaTendina.get(dropDownElementNumber).isDisplayed()) {

            System.out.println("ooooooooooooo");


            System.out.println(liElementsPrimaTendina.get(dropDownElementNumber) + "Elemento non cliccabile");
            chosenContainerPrimo.click();
            boolean d = isClickable(liElementsPrimaTendina.get(dropDownElementNumber));
            System.out.println(d);
        }

        boolean b = true;
        while (!(b=isClickable(liElementsPrimaTendina.get(dropDownElementNumber)))) {

            System.out.println("b="+b);
            System.out.println("ooooooooooooo");
            System.out.println(liElementsPrimaTendina.get(dropDownElementNumber) + "Elemento non cliccabile");
           // chosenContainerPrimo.click();

        }
        System.out.println("b="+b);

        while (!liElementsPrimaTendina.get(dropDownElementNumber).exists()) System.out.println("Eccezione");

///// Si blocca quando una di queste condizioni non è soddisfatta
        System.out.println(liElementsPrimaTendina.get(dropDownElementNumber).isEnabled());
        System.out.println(liElementsPrimaTendina.get(dropDownElementNumber).is(visible));
        System.out.println(liElementsPrimaTendina.get(dropDownElementNumber).is(enabled));
        System.out.println(liElementsPrimaTendina.get(dropDownElementNumber).exists());
        System.out.println(liElementsPrimaTendina.get(dropDownElementNumber).isDisplayed());
        System.out.println(liElementsPrimaTendina.get(dropDownElementNumber).is(hidden));
        System.out.println(liElementsPrimaTendina.get(dropDownElementNumber).is(clickable));
        ///////Se tutti i test sopra sono passati, perchè l'errore è nella seguente riga:
        liElementsPrimaTendina.get(dropDownElementNumber).click();


    }

////Funziona
    public static boolean isClickable(SelenideElement el) {
        try {
            el.click();
            el.click();
            return true;
        } catch (Throwable e) {
            System.out.println("Throwable");
            return false;
        }
    }


    public static boolean isClickableAssertion(SelenideElement el) {


        try {

            el.shouldBe(disabled);
            el.shouldBe(enabled);
            return true;
        } catch (AssertionError e) {
            System.out.println("oooo");
            return false;
        }
    }

    public SelenideElement searchButton(String buttonName) {
        SelenideElement selenideElement = $$("button").findBy(Condition.text(buttonName)).shouldBe(Condition.visible, Condition.exist);
        return selenideElement;
    }


    public SelenideElement searchTagWithSpecifiTextClickable(String tag, String text) {
        SelenideElement selenideElement = $$(tag).findBy(Condition.text(text)).shouldBe(Condition.exist, Condition.visible, clickable);
        return selenideElement;
    }


}

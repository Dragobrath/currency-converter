package ee.finestmedia.currencyconverter.controller;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import ee.finestmedia.currencyconverter.util.exception.MappingException;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.UnifiedCurrencyDataFeed;
import ee.finestmedia.currencyconverter.service.ConverterService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

  private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private ConverterService converterService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    LOG.info("Welcome home! The client locale is {}.", locale);

    UnifiedCurrencyDataFeed feed = converterService.convertCurrency("EUR", "LTL", 100, new DateTime(1392220747523L).minusYears(4).toDate());
    
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

    String formattedDate = dateFormat.format(date);

    model.addAttribute("feeds", feed.getUnifiedDataFeedEntries());
    model.addAttribute("serverTime", formattedDate);

    return "home";
  }

}

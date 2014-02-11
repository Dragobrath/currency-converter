package ee.finestmedia.currencyconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.ConverterService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

  private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private ConfigurationService configurationService;

  @Autowired
  private ConverterService converterService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    LOG.info("Welcome home! The client locale is {}.", locale);
    
    converterService.convertCurrency("EUR", "LTL", 100, new Date());

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate);

    return "home";
  }

}

package ee.finestmedia.currencyconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

import ee.finestmedia.currencyconverter.model.UnifiedDataFeed;
import ee.finestmedia.currencyconverter.service.ConverterService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

  private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

  private static final String HOME = "home";
  private static final String MESSAGE = "message";
  private static final String MESSAGE_SUCCESS = "success";
  private static final String MESSAGE_ERROR = "error.internal";
  private static final String MESSAGE_NO_COURSES_FOUND = "error.nocoursesfound";

  @Autowired
  private ConverterService converterService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    UnifiedDataFeed feed = new UnifiedDataFeed();
    try {
      feed = converterService.getCurrenciesList();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      model.addAttribute(MESSAGE, MESSAGE_ERROR);
    }

    if (feed.getUnifiedDataFeedEntries().isEmpty()) {
      LOG.error("Feeds are empty");
      model.addAttribute(MESSAGE, MESSAGE_NO_COURSES_FOUND);
    }

    model.addAttribute("feeds", feed.getUnifiedDataFeedEntries());
    model.addAttribute(MESSAGE, MESSAGE_SUCCESS);

    return HOME;
  }

}

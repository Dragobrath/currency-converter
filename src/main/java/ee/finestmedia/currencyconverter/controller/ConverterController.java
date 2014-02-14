package ee.finestmedia.currencyconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.finestmedia.currencyconverter.model.UIRequest;
import ee.finestmedia.currencyconverter.model.UIResponse;
import ee.finestmedia.currencyconverter.service.ConverterService;

@Controller
public class ConverterController {

  private static final Logger LOG = LoggerFactory.getLogger(ConverterController.class);
  private static final int RESULT_CODE_SUCCESS = 0;
  private static final int RESULT_CODE_ERROR = 1;

  @Autowired
  private ConverterService converterService;

  @RequestMapping(value = "/convert", method = RequestMethod.GET)
  public @ResponseBody
  UIResponse convert(@ModelAttribute("UIRequest") UIRequest request) {
    UIResponse response = new UIResponse();
    try {
      response = converterService.convertCurrency(request);
      response.setResultCode(RESULT_CODE_SUCCESS);
    } catch (Exception e) {
      response.setResultCode(RESULT_CODE_ERROR);
      LOG.error(e.getMessage(), e);
    }
    return response;
  }

}

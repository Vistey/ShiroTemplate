package com.controller.LookupTest;

import org.springframework.beans.factory.annotation.Lookup;


/**
 *
 */
public abstract class HjlCarrier {

  private WhlCarrier whlCarrier;

  @Lookup
  abstract WhlCarrier getEntity();

  WhlCarrier getWhlCarrier(){
    return this.getEntity();
  }
}

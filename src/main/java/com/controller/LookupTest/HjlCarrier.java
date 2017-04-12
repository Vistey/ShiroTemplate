package com.controller.LookupTest;

/**
 *
 */
public abstract class HjlCarrier {

  private WhlCarrier whlCarrier;

  public abstract WhlCarrier getEntity();

  WhlCarrier getWhlCarrier(){
    return this.getEntity();
  }
}

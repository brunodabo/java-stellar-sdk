// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package org.stellar.sdk.xdr;


import java.io.IOException;

import com.google.common.base.Objects;

// === xdr source ============================================================

//  typedef uint64 Duration;

//  ===========================================================================
public class Duration implements XdrElement {
  private Uint64 Duration;

  public Duration() {}

  public Duration(Uint64 Duration) {
    this.Duration = Duration;
  }

  public Uint64 getDuration() {
    return this.Duration;
  }

  public void setDuration(Uint64 value) {
    this.Duration = value;
  }

  public static void encode(XdrDataOutputStream stream, Duration  encodedDuration) throws IOException {
    Uint64.encode(stream, encodedDuration.Duration);
  }

  public void encode(XdrDataOutputStream stream) throws IOException {
    encode(stream, this);
  }
  public static Duration decode(XdrDataInputStream stream) throws IOException {
    Duration decodedDuration = new Duration();
    decodedDuration.Duration = Uint64.decode(stream);
    return decodedDuration;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.Duration);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Duration)) {
      return false;
    }

    Duration other = (Duration) object;
    return Objects.equal(this.Duration, other.Duration);
  }
}

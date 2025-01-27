// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package org.stellar.sdk.xdr;

import static org.stellar.sdk.xdr.Constants.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.stellar.sdk.Base64Factory;

// === xdr source ============================================================

//  union LedgerCloseMeta switch (int v)
//  {
//  case 0:
//      LedgerCloseMetaV0 v0;
//  case 1:
//      LedgerCloseMetaV1 v1;
//  case 2:
//      LedgerCloseMetaV2 v2;
//  };

//  ===========================================================================
public class LedgerCloseMeta implements XdrElement {
  public LedgerCloseMeta() {}

  Integer v;

  public Integer getDiscriminant() {
    return this.v;
  }

  public void setDiscriminant(Integer value) {
    this.v = value;
  }

  private LedgerCloseMetaV0 v0;

  public LedgerCloseMetaV0 getV0() {
    return this.v0;
  }

  public void setV0(LedgerCloseMetaV0 value) {
    this.v0 = value;
  }

  private LedgerCloseMetaV1 v1;

  public LedgerCloseMetaV1 getV1() {
    return this.v1;
  }

  public void setV1(LedgerCloseMetaV1 value) {
    this.v1 = value;
  }

  private LedgerCloseMetaV2 v2;

  public LedgerCloseMetaV2 getV2() {
    return this.v2;
  }

  public void setV2(LedgerCloseMetaV2 value) {
    this.v2 = value;
  }

  public static final class Builder {
    private Integer discriminant;
    private LedgerCloseMetaV0 v0;
    private LedgerCloseMetaV1 v1;
    private LedgerCloseMetaV2 v2;

    public Builder discriminant(Integer discriminant) {
      this.discriminant = discriminant;
      return this;
    }

    public Builder v0(LedgerCloseMetaV0 v0) {
      this.v0 = v0;
      return this;
    }

    public Builder v1(LedgerCloseMetaV1 v1) {
      this.v1 = v1;
      return this;
    }

    public Builder v2(LedgerCloseMetaV2 v2) {
      this.v2 = v2;
      return this;
    }

    public LedgerCloseMeta build() {
      LedgerCloseMeta val = new LedgerCloseMeta();
      val.setDiscriminant(discriminant);
      val.setV0(this.v0);
      val.setV1(this.v1);
      val.setV2(this.v2);
      return val;
    }
  }

  public static void encode(XdrDataOutputStream stream, LedgerCloseMeta encodedLedgerCloseMeta)
      throws IOException {
    // Xdrgen::AST::Typespecs::Int
    // Integer
    stream.writeInt(encodedLedgerCloseMeta.getDiscriminant().intValue());
    switch (encodedLedgerCloseMeta.getDiscriminant()) {
      case 0:
        LedgerCloseMetaV0.encode(stream, encodedLedgerCloseMeta.v0);
        break;
      case 1:
        LedgerCloseMetaV1.encode(stream, encodedLedgerCloseMeta.v1);
        break;
      case 2:
        LedgerCloseMetaV2.encode(stream, encodedLedgerCloseMeta.v2);
        break;
    }
  }

  public void encode(XdrDataOutputStream stream) throws IOException {
    encode(stream, this);
  }

  public static LedgerCloseMeta decode(XdrDataInputStream stream) throws IOException {
    LedgerCloseMeta decodedLedgerCloseMeta = new LedgerCloseMeta();
    Integer discriminant = stream.readInt();
    decodedLedgerCloseMeta.setDiscriminant(discriminant);
    switch (decodedLedgerCloseMeta.getDiscriminant()) {
      case 0:
        decodedLedgerCloseMeta.v0 = LedgerCloseMetaV0.decode(stream);
        break;
      case 1:
        decodedLedgerCloseMeta.v1 = LedgerCloseMetaV1.decode(stream);
        break;
      case 2:
        decodedLedgerCloseMeta.v2 = LedgerCloseMetaV2.decode(stream);
        break;
    }
    return decodedLedgerCloseMeta;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.v0, this.v1, this.v2, this.v);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof LedgerCloseMeta)) {
      return false;
    }

    LedgerCloseMeta other = (LedgerCloseMeta) object;
    return Objects.equals(this.v0, other.v0)
        && Objects.equals(this.v1, other.v1)
        && Objects.equals(this.v2, other.v2)
        && Objects.equals(this.v, other.v);
  }

  @Override
  public String toXdrBase64() throws IOException {
    return Base64Factory.getInstance().encodeToString(toXdrByteArray());
  }

  @Override
  public byte[] toXdrByteArray() throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    XdrDataOutputStream xdrDataOutputStream = new XdrDataOutputStream(byteArrayOutputStream);
    encode(xdrDataOutputStream);
    return byteArrayOutputStream.toByteArray();
  }

  public static LedgerCloseMeta fromXdrBase64(String xdr) throws IOException {
    byte[] bytes = Base64Factory.getInstance().decode(xdr);
    return fromXdrByteArray(bytes);
  }

  public static LedgerCloseMeta fromXdrByteArray(byte[] xdr) throws IOException {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xdr);
    XdrDataInputStream xdrDataInputStream = new XdrDataInputStream(byteArrayInputStream);
    return decode(xdrDataInputStream);
  }
}

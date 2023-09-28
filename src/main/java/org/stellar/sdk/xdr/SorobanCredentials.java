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

//  union SorobanCredentials switch (SorobanCredentialsType type)
//  {
//  case SOROBAN_CREDENTIALS_SOURCE_ACCOUNT:
//      void;
//  case SOROBAN_CREDENTIALS_ADDRESS:
//      SorobanAddressCredentials address;
//  };

//  ===========================================================================
public class SorobanCredentials implements XdrElement {
  public SorobanCredentials() {}

  SorobanCredentialsType type;

  public SorobanCredentialsType getDiscriminant() {
    return this.type;
  }

  public void setDiscriminant(SorobanCredentialsType value) {
    this.type = value;
  }

  private SorobanAddressCredentials address;

  public SorobanAddressCredentials getAddress() {
    return this.address;
  }

  public void setAddress(SorobanAddressCredentials value) {
    this.address = value;
  }

  public static final class Builder {
    private SorobanCredentialsType discriminant;
    private SorobanAddressCredentials address;

    public Builder discriminant(SorobanCredentialsType discriminant) {
      this.discriminant = discriminant;
      return this;
    }

    public Builder address(SorobanAddressCredentials address) {
      this.address = address;
      return this;
    }

    public SorobanCredentials build() {
      SorobanCredentials val = new SorobanCredentials();
      val.setDiscriminant(discriminant);
      val.setAddress(this.address);
      return val;
    }
  }

  public static void encode(
      XdrDataOutputStream stream, SorobanCredentials encodedSorobanCredentials) throws IOException {
    // Xdrgen::AST::Identifier
    // SorobanCredentialsType
    stream.writeInt(encodedSorobanCredentials.getDiscriminant().getValue());
    switch (encodedSorobanCredentials.getDiscriminant()) {
      case SOROBAN_CREDENTIALS_SOURCE_ACCOUNT:
        break;
      case SOROBAN_CREDENTIALS_ADDRESS:
        SorobanAddressCredentials.encode(stream, encodedSorobanCredentials.address);
        break;
    }
  }

  public void encode(XdrDataOutputStream stream) throws IOException {
    encode(stream, this);
  }

  public static SorobanCredentials decode(XdrDataInputStream stream) throws IOException {
    SorobanCredentials decodedSorobanCredentials = new SorobanCredentials();
    SorobanCredentialsType discriminant = SorobanCredentialsType.decode(stream);
    decodedSorobanCredentials.setDiscriminant(discriminant);
    switch (decodedSorobanCredentials.getDiscriminant()) {
      case SOROBAN_CREDENTIALS_SOURCE_ACCOUNT:
        break;
      case SOROBAN_CREDENTIALS_ADDRESS:
        decodedSorobanCredentials.address = SorobanAddressCredentials.decode(stream);
        break;
    }
    return decodedSorobanCredentials;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.address, this.type);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof SorobanCredentials)) {
      return false;
    }

    SorobanCredentials other = (SorobanCredentials) object;
    return Objects.equals(this.address, other.address) && Objects.equals(this.type, other.type);
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

  public static SorobanCredentials fromXdrBase64(String xdr) throws IOException {
    byte[] bytes = Base64Factory.getInstance().decode(xdr);
    return fromXdrByteArray(bytes);
  }

  public static SorobanCredentials fromXdrByteArray(byte[] xdr) throws IOException {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xdr);
    XdrDataInputStream xdrDataInputStream = new XdrDataInputStream(byteArrayInputStream);
    return decode(xdrDataInputStream);
  }
}
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

//  union SCError switch (SCErrorType type)
//  {
//  case SCE_CONTRACT:
//      uint32 contractCode;
//  case SCE_WASM_VM:
//  case SCE_CONTEXT:
//  case SCE_STORAGE:
//  case SCE_OBJECT:
//  case SCE_CRYPTO:
//  case SCE_EVENTS:
//  case SCE_BUDGET:
//  case SCE_VALUE:
//  case SCE_AUTH:
//      SCErrorCode code;
//  };

//  ===========================================================================
public class SCError implements XdrElement {
  public SCError() {}

  SCErrorType type;

  public SCErrorType getDiscriminant() {
    return this.type;
  }

  public void setDiscriminant(SCErrorType value) {
    this.type = value;
  }

  private Uint32 contractCode;

  public Uint32 getContractCode() {
    return this.contractCode;
  }

  public void setContractCode(Uint32 value) {
    this.contractCode = value;
  }

  private SCErrorCode code;

  public SCErrorCode getCode() {
    return this.code;
  }

  public void setCode(SCErrorCode value) {
    this.code = value;
  }

  public static final class Builder {
    private SCErrorType discriminant;
    private Uint32 contractCode;
    private SCErrorCode code;

    public Builder discriminant(SCErrorType discriminant) {
      this.discriminant = discriminant;
      return this;
    }

    public Builder contractCode(Uint32 contractCode) {
      this.contractCode = contractCode;
      return this;
    }

    public Builder code(SCErrorCode code) {
      this.code = code;
      return this;
    }

    public SCError build() {
      SCError val = new SCError();
      val.setDiscriminant(discriminant);
      val.setContractCode(this.contractCode);
      val.setCode(this.code);
      return val;
    }
  }

  public static void encode(XdrDataOutputStream stream, SCError encodedSCError) throws IOException {
    // Xdrgen::AST::Identifier
    // SCErrorType
    stream.writeInt(encodedSCError.getDiscriminant().getValue());
    switch (encodedSCError.getDiscriminant()) {
      case SCE_CONTRACT:
        Uint32.encode(stream, encodedSCError.contractCode);
        break;
      case SCE_WASM_VM:
      case SCE_CONTEXT:
      case SCE_STORAGE:
      case SCE_OBJECT:
      case SCE_CRYPTO:
      case SCE_EVENTS:
      case SCE_BUDGET:
      case SCE_VALUE:
      case SCE_AUTH:
        SCErrorCode.encode(stream, encodedSCError.code);
        break;
    }
  }

  public void encode(XdrDataOutputStream stream) throws IOException {
    encode(stream, this);
  }

  public static SCError decode(XdrDataInputStream stream) throws IOException {
    SCError decodedSCError = new SCError();
    SCErrorType discriminant = SCErrorType.decode(stream);
    decodedSCError.setDiscriminant(discriminant);
    switch (decodedSCError.getDiscriminant()) {
      case SCE_CONTRACT:
        decodedSCError.contractCode = Uint32.decode(stream);
        break;
      case SCE_WASM_VM:
      case SCE_CONTEXT:
      case SCE_STORAGE:
      case SCE_OBJECT:
      case SCE_CRYPTO:
      case SCE_EVENTS:
      case SCE_BUDGET:
      case SCE_VALUE:
      case SCE_AUTH:
        decodedSCError.code = SCErrorCode.decode(stream);
        break;
    }
    return decodedSCError;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.contractCode, this.code, this.type);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof SCError)) {
      return false;
    }

    SCError other = (SCError) object;
    return Objects.equals(this.contractCode, other.contractCode)
        && Objects.equals(this.code, other.code)
        && Objects.equals(this.type, other.type);
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

  public static SCError fromXdrBase64(String xdr) throws IOException {
    byte[] bytes = Base64Factory.getInstance().decode(xdr);
    return fromXdrByteArray(bytes);
  }

  public static SCError fromXdrByteArray(byte[] xdr) throws IOException {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xdr);
    XdrDataInputStream xdrDataInputStream = new XdrDataInputStream(byteArrayInputStream);
    return decode(xdrDataInputStream);
  }
}
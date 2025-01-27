package org.stellar.sdk;

import java.util.Objects;
import lombok.NonNull;
import org.stellar.sdk.xdr.AssetType;

/**
 * Class for LiquidityPoolShareTrustLineAsset
 *
 * @see <a href="https://developers.stellar.org/docs/glossary/liquidity-pool/"
 *     target="_blank">Liquidity Pool</a>
 */
public final class LiquidityPoolShareTrustLineAsset extends TrustLineAsset {
  protected final LiquidityPoolID mId;

  /**
   * Constructor
   *
   * @param params the LiquidityPoolParameters
   */
  public LiquidityPoolShareTrustLineAsset(@NonNull LiquidityPoolParameters params) {
    mId = params.getId();
  }

  /**
   * Constructor
   *
   * @param id the LiquidityPoolID
   */
  public LiquidityPoolShareTrustLineAsset(@NonNull LiquidityPoolID id) {
    mId = id;
  }

  /**
   * Get the liquidity pool id
   *
   * @return LiquidityPoolID
   */
  public LiquidityPoolID getLiquidityPoolID() {
    return mId;
  }

  @Override
  public String getType() {
    return "pool_share";
  }

  @Override
  public String toString() {
    return this.getLiquidityPoolID().toString();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.mId);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || !this.getClass().equals(object.getClass())) {
      return false;
    }

    LiquidityPoolShareTrustLineAsset o = (LiquidityPoolShareTrustLineAsset) object;

    return this.toString() == o.toString();
  }

  @Override
  public int compareTo(TrustLineAsset other) {
    if (!Objects.equals(other.getType(), "pool_share")) {
      return 1;
    }
    return this.toString().compareTo(((LiquidityPoolShareTrustLineAsset) other).toString());
  }

  @Override
  public org.stellar.sdk.xdr.TrustLineAsset toXdr() {
    org.stellar.sdk.xdr.TrustLineAsset xdr = new org.stellar.sdk.xdr.TrustLineAsset();
    xdr.setDiscriminant(AssetType.ASSET_TYPE_POOL_SHARE);
    xdr.setLiquidityPoolID(mId.toXdr());
    return xdr;
  }
}

package org.stellar.sdk.responses.operations;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.Objects;
import org.stellar.sdk.AssetAmount;
import org.stellar.sdk.LiquidityPoolID;

public class LiquidityPoolWithdrawOperationResponse extends OperationResponse {
  @SerializedName("liquidity_pool_id")
  private final LiquidityPoolID liquidityPoolId;

  @SerializedName("reserves_min")
  private final AssetAmount[] reservesMin;

  @SerializedName("reserves_received")
  private final AssetAmount[] reservesReceived;

  @SerializedName("shares")
  private final String shares;

  public LiquidityPoolWithdrawOperationResponse(
      LiquidityPoolID poolId,
      AssetAmount[] reservesMin,
      String shares,
      AssetAmount[] reservesReceived) {
    this.liquidityPoolId = poolId;
    this.reservesMin = reservesMin;
    this.shares = shares;
    this.reservesReceived = reservesReceived;
  }

  public LiquidityPoolID getLiquidityPoolId() {
    return liquidityPoolId;
  }

  public AssetAmount[] getReservesMin() {
    return reservesMin;
  }

  public AssetAmount[] getReservesReceived() {
    return reservesReceived;
  }

  public String getShares() {
    return shares;
  }

  public int hashCode() {
    return Objects.hash(
        liquidityPoolId, Arrays.hashCode(reservesMin), Arrays.hashCode(reservesReceived), shares);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof LiquidityPoolDepositOperationResponse)) {
      return false;
    }

    LiquidityPoolWithdrawOperationResponse o = (LiquidityPoolWithdrawOperationResponse) object;
    return Objects.equals(this.getLiquidityPoolId(), o.getLiquidityPoolId())
        && Arrays.equals(this.getReservesMin(), o.getReservesMin())
        && Arrays.equals(this.getReservesReceived(), o.getReservesReceived())
        && Objects.equals(this.getShares(), o.getShares());
  }
}

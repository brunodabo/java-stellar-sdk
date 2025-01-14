package org.stellar.sdk;

import java.util.Objects;
import lombok.NonNull;
import org.stellar.sdk.xdr.ChangeTrustOp;
import org.stellar.sdk.xdr.Int64;
import org.stellar.sdk.xdr.OperationType;

/**
 * Represents <a href="https://developers.stellar.org/docs/start/list-of-operations/#change-trust"
 * target="_blank">ChangeTrust</a> operation.
 *
 * @see <a href="https://developers.stellar.org/docs/start/list-of-operations/" target="_blank">List
 *     of Operations</a>
 */
public class ChangeTrustOperation extends Operation {

  private final ChangeTrustAsset asset;
  private final String limit;

  private ChangeTrustOperation(@NonNull ChangeTrustAsset asset, @NonNull String limit) {
    this.asset = asset;
    this.limit = limit;
  }

  /**
   * The asset of the trustline. For example, if a gateway extends a trustline of up to 200 USD to a
   * user, the line is USD.
   */
  public ChangeTrustAsset getAsset() {
    return asset;
  }

  /**
   * The limit of the trustline. For example, if a gateway extends a trustline of up to 200 USD to a
   * user, the limit is 200.
   */
  public String getLimit() {
    return limit;
  }

  @Override
  org.stellar.sdk.xdr.Operation.OperationBody toOperationBody(AccountConverter accountConverter) {
    ChangeTrustOp op = new ChangeTrustOp();
    op.setLine(asset.toXdr());
    Int64 limit = new Int64();
    limit.setInt64(Operation.toXdrAmount(this.limit));
    op.setLimit(limit);

    org.stellar.sdk.xdr.Operation.OperationBody body =
        new org.stellar.sdk.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.CHANGE_TRUST);
    body.setChangeTrustOp(op);
    return body;
  }

  /**
   * Builds ChangeTrust operation.
   *
   * @see ChangeTrustOperation
   */
  public static class Builder {
    private final ChangeTrustAsset asset;
    private final String limit;

    private String mSourceAccount;

    Builder(ChangeTrustOp op) {
      asset = ChangeTrustAsset.fromXdr(op.getLine());
      limit = Operation.fromXdrAmount(op.getLimit().getInt64().longValue());
    }

    /**
     * Creates a new ChangeTrust builder.
     *
     * @param asset The asset of the trustline. For example, if a gateway extends a trustline of up
     *     to 200 USD to a user, the line is USD.
     * @param limit The limit of the trustline. For example, if a gateway extends a trustline of up
     *     to 200 USD to a user, the limit is 200.
     * @throws ArithmeticException when limit has more than 7 decimal places.
     */
    public Builder(@NonNull ChangeTrustAsset asset, @NonNull String limit) {
      this.asset = asset;
      this.limit = limit;
    }

    /**
     * Set source account of this operation
     *
     * @param sourceAccount Source account
     * @return Builder object so you can chain methods.
     */
    public Builder setSourceAccount(@NonNull String sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /** Builds an operation */
    public ChangeTrustOperation build() {
      ChangeTrustOperation operation = new ChangeTrustOperation(asset, limit);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.asset, this.limit, this.getSourceAccount());
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof ChangeTrustOperation)) {
      return false;
    }

    ChangeTrustOperation other = (ChangeTrustOperation) object;
    return Objects.equals(this.asset, other.asset)
        && Objects.equals(this.limit, other.limit)
        && Objects.equals(this.getSourceAccount(), other.getSourceAccount());
  }
}

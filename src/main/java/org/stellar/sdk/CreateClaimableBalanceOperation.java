package org.stellar.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import org.stellar.sdk.xdr.*;

public class CreateClaimableBalanceOperation extends Operation {
  private final String amount;
  private final Asset asset;
  private final List<Claimant> claimants;

  private CreateClaimableBalanceOperation(
      @NonNull String amount, @NonNull Asset asset, @NonNull List<Claimant> claimants) {
    this.asset = asset;
    this.amount = amount;
    this.claimants = claimants;
    if (this.claimants.isEmpty()) {
      throw new IllegalArgumentException("claimants cannot be empty");
    }
  }

  public Asset getAsset() {
    return asset;
  }

  public String getAmount() {
    return amount;
  }

  public List<Claimant> getClaimants() {
    return claimants;
  }

  @Override
  org.stellar.sdk.xdr.Operation.OperationBody toOperationBody(AccountConverter accountConverter) {
    CreateClaimableBalanceOp op = new CreateClaimableBalanceOp();

    // asset
    op.setAsset(asset.toXdr());
    // amount
    Int64 amount = new Int64();
    amount.setInt64(Operation.toXdrAmount(this.amount));
    op.setAmount(amount);

    org.stellar.sdk.xdr.Claimant[] xdrClaimants =
        new org.stellar.sdk.xdr.Claimant[claimants.size()];
    for (int i = 0; i < claimants.size(); i++) {

      org.stellar.sdk.xdr.Claimant.ClaimantV0 v0 = new org.stellar.sdk.xdr.Claimant.ClaimantV0();
      v0.setDestination(StrKey.encodeToXDRAccountId(claimants.get(i).getDestination()));
      v0.setPredicate(claimants.get(i).getPredicate().toXdr());

      xdrClaimants[i] = new org.stellar.sdk.xdr.Claimant();
      xdrClaimants[i].setDiscriminant(ClaimantType.CLAIMANT_TYPE_V0);
      xdrClaimants[i].setV0(v0);
    }
    op.setClaimants(xdrClaimants);

    org.stellar.sdk.xdr.Operation.OperationBody body =
        new org.stellar.sdk.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.CREATE_CLAIMABLE_BALANCE);
    body.setCreateClaimableBalanceOp(op);
    return body;
  }

  public static class Builder {
    private final String amount;
    private final Asset asset;
    private final List<Claimant> claimants;

    private String mSourceAccount;

    /**
     * Construct a new CreateClaimableBalance builder from a CreateClaimableBalance XDR.
     *
     * @param op {@link CreateClaimableBalanceOp}
     */
    Builder(CreateClaimableBalanceOp op) {
      asset = Asset.fromXdr(op.getAsset());
      amount = Operation.fromXdrAmount(op.getAmount().getInt64().longValue());
      claimants = new ArrayList<>();
      for (org.stellar.sdk.xdr.Claimant c : op.getClaimants()) {
        claimants.add(
            new Claimant(
                StrKey.encodeStellarAccountId(c.getV0().getDestination()),
                Predicate.fromXdr(c.getV0().getPredicate())));
      }
    }

    /**
     * Creates a new CreateClaimableBalance builder.
     *
     * @param amount The amount which can be claimed.
     * @param asset The asset which can be claimed/
     * @param claimants The list of entities which can claim the balance.
     */
    public Builder(String amount, Asset asset, List<Claimant> claimants) {
      this.amount = amount;
      this.asset = asset;
      this.claimants = claimants;
    }

    /**
     * Sets the source account for this operation.
     *
     * @param sourceAccount The operation's source account.
     * @return Builder object so you can chain methods.
     */
    public CreateClaimableBalanceOperation.Builder setSourceAccount(@NonNull String sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /** Builds an operation */
    public CreateClaimableBalanceOperation build() {
      CreateClaimableBalanceOperation operation =
          new CreateClaimableBalanceOperation(amount, asset, claimants);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.amount, this.asset, this.claimants, this.getSourceAccount());
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof CreateClaimableBalanceOperation)) {
      return false;
    }

    CreateClaimableBalanceOperation other = (CreateClaimableBalanceOperation) object;
    return Objects.equals(this.amount, other.amount)
        && Objects.equals(this.asset, other.asset)
        && Objects.equals(this.claimants, other.claimants)
        && Objects.equals(this.getSourceAccount(), other.getSourceAccount());
  }
}

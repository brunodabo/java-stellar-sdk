package org.stellar.sdk;

import java.io.IOException;
import java.util.Objects;
import lombok.NonNull;
import org.stellar.sdk.xdr.*;

public class RevokeClaimableBalanceSponsorshipOperation extends Operation {
  private final String balanceId;

  private RevokeClaimableBalanceSponsorshipOperation(String balanceId) {
    this.balanceId = balanceId;
  }

  public String getBalanceId() {
    return balanceId;
  }

  @Override
  org.stellar.sdk.xdr.Operation.OperationBody toOperationBody(AccountConverter accountConverter) {
    byte[] balanceIdBytes = Util.hexToBytes(this.balanceId);
    ClaimableBalanceID balanceId;
    try {
      balanceId = ClaimableBalanceID.fromXdrByteArray(balanceIdBytes);
    } catch (IOException e) {
      throw new IllegalArgumentException("invalid balanceId: " + this.balanceId, e);
    }

    RevokeSponsorshipOp op = new RevokeSponsorshipOp();
    LedgerKey key = new LedgerKey();
    key.setDiscriminant(LedgerEntryType.CLAIMABLE_BALANCE);
    LedgerKey.LedgerKeyClaimableBalance claimableBalance =
        new LedgerKey.LedgerKeyClaimableBalance();

    claimableBalance.setBalanceID(balanceId);
    key.setClaimableBalance(claimableBalance);
    op.setLedgerKey(key);
    op.setDiscriminant(RevokeSponsorshipType.REVOKE_SPONSORSHIP_LEDGER_ENTRY);

    org.stellar.sdk.xdr.Operation.OperationBody body =
        new org.stellar.sdk.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.REVOKE_SPONSORSHIP);
    body.setRevokeSponsorshipOp(op);

    return body;
  }

  public static class Builder {
    private final String balanceId;

    private String mSourceAccount;

    /**
     * Construct a new RevokeClaimableBalanceSponsorshipOperation builder from a RevokeSponsorship
     * XDR.
     *
     * @param op {@link RevokeSponsorshipOp}
     */
    Builder(RevokeSponsorshipOp op) {
      try {
        balanceId =
            Util.bytesToHex(op.getLedgerKey().getClaimableBalance().getBalanceID().toXdrByteArray())
                .toLowerCase();
      } catch (IOException e) {
        throw new IllegalArgumentException("Invalid claimableBalance in the operation", e);
      }
    }

    /**
     * Creates a new RevokeClaimableBalanceSponsorshipOperation builder.
     *
     * @param balanceId The id of the claimable balance whose sponsorship will be revoked.
     */
    public Builder(String balanceId) {
      this.balanceId = balanceId;
    }

    /**
     * Sets the source account for this operation.
     *
     * @param sourceAccount The operation's source account.
     * @return Builder object so you can chain methods.
     */
    public RevokeClaimableBalanceSponsorshipOperation.Builder setSourceAccount(
        @NonNull String sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /** Builds an operation */
    public RevokeClaimableBalanceSponsorshipOperation build() {
      RevokeClaimableBalanceSponsorshipOperation operation =
          new RevokeClaimableBalanceSponsorshipOperation(balanceId);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.balanceId, this.getSourceAccount());
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof RevokeClaimableBalanceSponsorshipOperation)) {
      return false;
    }

    RevokeClaimableBalanceSponsorshipOperation other =
        (RevokeClaimableBalanceSponsorshipOperation) object;
    return Objects.equals(this.balanceId, other.balanceId)
        && Objects.equals(this.getSourceAccount(), other.getSourceAccount());
  }
}

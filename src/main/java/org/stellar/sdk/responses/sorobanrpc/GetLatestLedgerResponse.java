package org.stellar.sdk.responses.sorobanrpc;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Response for JSON-RPC method getLatestLedger.
 *
 * @see <a href="https://soroban.stellar.org/api/methods/getLatestLedger#returns"
 *     target="_blank">getLatestLedger documentation</a>
 */
@AllArgsConstructor
@Value
public class GetLatestLedgerResponse {
  String id;

  Integer protocolVersion;

  Integer sequence;
}

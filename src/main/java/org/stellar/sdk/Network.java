package org.stellar.sdk;

import java.nio.charset.Charset;
import java.util.Objects;
import lombok.NonNull;

/**
 * Network class is used to specify which Stellar network you want to use. Each network has a <code>
 * networkPassphrase</code> which is hashed to every transaction id.
 */
public class Network {
  public static final Network PUBLIC =
      new Network("Public Global Stellar Network ; September 2015");
  public static final Network TESTNET = new Network("Test SDF Network ; September 2015");
  public static final Network FUTURENET = new Network("Test SDF Future Network ; October 2022");
  public static final Network STANDALONE = new Network("Standalone Network ; February 2017");
  public static final Network SANDBOX =
      new Network("Local Sandbox Stellar Network ; September 2022");

  private final String networkPassphrase;

  /**
   * Creates a new Network object to represent a network with a given passphrase
   *
   * @param networkPassphrase
   */
  public Network(@NonNull String networkPassphrase) {
    this.networkPassphrase = networkPassphrase;
  }

  /** Returns network passphrase */
  public String getNetworkPassphrase() {
    return networkPassphrase;
  }

  /** Returns network id (SHA-256 hashed <code>networkPassphrase</code>). */
  public byte[] getNetworkId() {
    return Util.hash(this.networkPassphrase.getBytes(Charset.forName("UTF-8")));
  }

  @Override
  public int hashCode() {
    return this.networkPassphrase.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Network)) {
      return false;
    }

    Network other = (Network) object;
    return Objects.equals(this.networkPassphrase, other.networkPassphrase);
  }

  @Override
  public String toString() {
    return this.networkPassphrase;
  }
}

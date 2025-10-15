package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password321";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode() % _passwords.length);
        Account<K, V> current = _passwords[index];

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                current.setPassword(value);
                return;
            }
            if (current.getNext() == null) {
                break;
            }
            current = current.getNext();
        }

        Account<K, V> newAccount = new Account<>(key, value);
        if (current == null) {
            _passwords[index] = newAccount;
        } else {
            current.setNext(newAccount);
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode() % _passwords.length);
        Account<K, V> current = _passwords[index];

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                return current.getPassword();
            }
            current = current.getNext();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int count = 0;
        for (Account<K, V> chain : _passwords) {
            while (chain != null) {
                count++;
                chain = chain.getNext();
            }
        }
        return count;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Account<K, V> chain : _passwords) {
            while (chain != null) {
                keys.add(chain.getWebsite());
                chain = chain.getNext();
            }
        }
        return keys;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode() % _passwords.length);
        Account<K, V> current = _passwords[index];
        Account<K, V> previous = null;

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                if (previous == null) {
                    _passwords[index] = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return current.getPassword();
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicates(V value) {
        List<K> duplicates = new ArrayList<>();

        for (Account<K, V> chain : _passwords) {
            while (chain != null) {
                if (chain.getPassword().equals(value)) {
                    duplicates.add(chain.getWebsite());
                }
                chain = chain.getNext();
            }
        }
        return duplicates;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return MASTER_PASSWORD.equals(enteredPassword);
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        // TODO:
        int leftLimit = 48;
        int rightLimit = 122;
        // int leftLimit = ; // hint: numeral '0'=48
        // int rightLimit = ; // hint: letter 'z'=122
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4
        if (length < 4) {
            length = 4;
        }

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}

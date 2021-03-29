package Firebase;

import com.google.firebase.auth.FirebaseAuth;

public class Autenticacion
{
    private FirebaseAuth mAuth;

    public FirebaseAuth getmAuth()
    {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth)
    {
        this.mAuth = mAuth;
    }
}

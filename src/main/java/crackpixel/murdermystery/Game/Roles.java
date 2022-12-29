package crackpixel.murdermystery.Game;

public enum Roles {
    INNOCENT,
    DETECTIVE,
    MURDER;

    public Roles getRoleByNumer(int num){
        switch(num){
            case 0:
                return Roles.INNOCENT;
            case 1:
                return Roles.DETECTIVE;
            case 2:
                return Roles.MURDER;
        }
        return null;
    }
}

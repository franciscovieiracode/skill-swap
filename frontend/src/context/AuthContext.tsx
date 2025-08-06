import React, { createContext, useState, useEffect, type ReactNode } from "react";
import userApi from "../api/userApi";

interface AuthContextType {
  user: any; // Replace `any` with your actual User type if available
  setUser: React.Dispatch<React.SetStateAction<any>>;
  loading: boolean;
}

export const AuthContext = createContext<AuthContextType | null>(null);

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [user, setUser] = useState<any>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await userApi.getCurrentUser();
        setUser(res);
        console.log(res);
        
      } catch (err) {
        console.error("Failed to fetch current user", err);
        setUser(null);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, []);

  return (
    <AuthContext.Provider value={{ user, setUser, loading }}>
      {children}
    </AuthContext.Provider>
  );
};
